package it.prova.gestionetriage.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.dto.DottoreResponseDTO;
import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.service.PazienteService;

@RestController
@RequestMapping("/api/assegnaPaziente")
public class AssegnaPazienteController {

	@Autowired
	private WebClient webClient;

	@Autowired
	private PazienteService pazienteService;

	@GetMapping("/{cd}")
	public PazienteDTO assegnaPaziente(
			@PathVariable(value = "codiceDottore", required = true) String codiceDottore, Long id) {

		Paziente paziente = pazienteService.caricaSingoloElemento(id);

		DottoreResponseDTO dottoreResponseDTO = webClient.get()
				.uri("/" + "verificaDisponibilita" + paziente.getCodiceDottore()).retrieve()
				.bodyToMono(DottoreResponseDTO.class).block();
		
		dottoreResponseDTO = webClient.get()
				.uri("/" + "impostaInVisita" + paziente.getCodiceDottore()).retrieve()
				.bodyToMono(DottoreResponseDTO.class).block();

		PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(paziente);

		// se l'altra banca ha trovato qualcosa io riempio il DTO altrimenti no
		if (dottoreResponseDTO != null && dottoreResponseDTO.getCodiceDottore() != null
				&& dottoreResponseDTO.getCodiceDottore().equals(result.getCodiceDottore())) {
			result.setCodiceDottore(dottoreResponseDTO.getCodiceDottore());
		}
		
		paziente.setStato(StatoPaziente.IN_VISITA);
		pazienteService.aggiorna(paziente);
		return result;
	}

}
