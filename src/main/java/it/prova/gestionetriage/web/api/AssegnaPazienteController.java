package it.prova.gestionetriage.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.dto.DottoreResponseDTO;
import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.exception.PazienteNotFoundException;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.service.PazienteService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/assegnaPaziente")
public class AssegnaPazienteController {

	@Autowired
	private WebClient webClient;

	@Autowired
	private PazienteService pazienteService;

	@GetMapping("assegnaPaziente/{codiceDottore}/{id}")
	public PazienteDTO assegnaPaziente(
			@PathVariable(value = "codiceDottore", required = true) String codiceDottore, @PathVariable(value = "id", required = true)Long id) {

		Paziente paziente = pazienteService.caricaSingoloElemento(id);
		
		DottoreResponseDTO dottoreResponseDTO = webClient.get()
				.uri("/" + "verificaDisponibilita/" + codiceDottore).retrieve()
				.bodyToMono(DottoreResponseDTO.class).block();
		
		ResponseEntity<DottoreResponseDTO> response = webClient.post()
				.uri("/" + "impostaInVisita")
				.body(Mono.just(dottoreResponseDTO), DottoreResponseDTO.class)
				.retrieve().toEntity(DottoreResponseDTO.class).block();

		// se l'altra banca ha trovato qualcosa io riempio il DTO altrimenti no
		if (dottoreResponseDTO != null && dottoreResponseDTO.getCodiceDottore() != null) {
			paziente.setCodiceDottore(dottoreResponseDTO.getCodiceDottore());
			paziente.setStato(StatoPaziente.IN_VISITA);
			pazienteService.aggiorna(paziente);
		}
		
		
		PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(paziente);
		return result;
	}
	
	@GetMapping("ricovera/{id}")
	public PazienteDTO ricovera(
			@PathVariable(value = "id", required = true) Long id) {

		Paziente paziente = pazienteService.caricaSingoloElemento(id);
		
		if(paziente == null || paziente.getId() == null) {
			throw new PazienteNotFoundException("paziente non trovato");
		}
		
		DottoreResponseDTO dottoreResponseDTO = webClient.get()
				.uri("/" + "terminaVisita"+ "/" + paziente.getCodiceDottore()).retrieve()
				.bodyToMono(DottoreResponseDTO.class).block();

		PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(paziente);

//		 se l'altra banca ha trovato qualcosa io riempio il DTO altrimenti no
		if (dottoreResponseDTO != null && dottoreResponseDTO.getCodiceDottore() != null
				&& dottoreResponseDTO.getCodiceDottore().equals(result.getCodiceDottore())) {
			result.setStato(StatoPaziente.RICOVERATO);
			result.setCodiceDottore(null);
		}
		
		pazienteService.aggiorna(paziente);
		return result;
	}

}
