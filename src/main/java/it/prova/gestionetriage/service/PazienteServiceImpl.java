package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.repository.paziente.PazienteRepository;

@Service
@Transactional(readOnly = true)
public class PazienteServiceImpl implements PazienteService{
	
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
	public void aggiorna(Paziente pazienteInstance) {
		pazienteRepository.save(pazienteInstance);
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Paziente pazienteInstance) {
		pazienteRepository.save(pazienteInstance);
		
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}

}
