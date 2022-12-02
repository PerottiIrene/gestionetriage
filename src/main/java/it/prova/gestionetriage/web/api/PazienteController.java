package it.prova.gestionetriage.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.exception.IdNotNullForInsertException;
import it.prova.gestionetriage.exception.OperazioneNegataException;
import it.prova.gestionetriage.exception.PazienteNotFoundException;
import it.prova.gestionetriage.exception.PazienteNotFoundException;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.service.PazienteService;
import it.prova.gestionetriage.service.PazienteService;

@RestController
@RequestMapping("/api/paziente")
public class PazienteController {

	@Autowired
	private PazienteService pazienteService;

	@GetMapping
	public List<PazienteDTO> getAll() {
		return PazienteDTO.createPazienteDTOListFromModelList(pazienteService.listAll());
	}

	// gli errori di validazione vengono mostrati con 400 Bad Request ma
	// elencandoli grazie al ControllerAdvice
	@PostMapping
	public PazienteDTO createNew(@Valid @RequestBody PazienteDTO pazienteInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (pazienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Paziente pazienteInserito = pazienteService.inserisciNuovo(pazienteInput.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteInserito);

	}

	@GetMapping("/{id}")
	public PazienteDTO findById(@PathVariable(value = "id", required = true) long id) {

		Paziente paziente = pazienteService.caricaSingoloElemento(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		return PazienteDTO.buildPazienteDTOFromModel(paziente);

	}

	@PutMapping("/{id}")
	public PazienteDTO update(@Valid @RequestBody PazienteDTO pazienteInput, @PathVariable(required = true) Long id) {

		Paziente paziente = pazienteService.caricaSingoloElemento(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		pazienteInput.setId(id);
		Paziente pazienteAggiornato = pazienteService.aggiorna(pazienteInput.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteAggiornato);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) throws OperazioneNegataException {

		pazienteService.rimuovi(id);

	}

}
