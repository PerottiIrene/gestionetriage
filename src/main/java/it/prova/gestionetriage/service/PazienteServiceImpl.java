package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetriage.exception.PazienteNonDimessoException;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.repository.paziente.PazienteRepository;

@Service
@Transactional(readOnly = true)
public class PazienteServiceImpl implements PazienteService {

	@Autowired
	private PazienteRepository pazienteRepository;

	@Override
	public List<Paziente> listAll() {
		return (List<Paziente>) pazienteRepository.findAll();
	}

	@Override
	public Paziente caricaSingoloElemento(Long id) {
		return pazienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Paziente aggiorna(Paziente pazienteInstance) {
		return pazienteRepository.save(pazienteInstance);

	}

	@Override
	@Transactional
	public Paziente inserisciNuovo(Paziente pazienteInstance) {

		pazienteInstance.setStato(StatoPaziente.IN_ATTESA_VISITA);
		return pazienteRepository.save(pazienteInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {

		Paziente pazienteInstance = caricaSingoloElemento(idToRemove);
		if (!(pazienteInstance.getStato() == StatoPaziente.DIMESSO)) {
			throw new PazienteNonDimessoException("il paziente non e' stato dimesso, impossibile eliminare");
		}
		pazienteRepository.deleteById(idToRemove);

	}

}
