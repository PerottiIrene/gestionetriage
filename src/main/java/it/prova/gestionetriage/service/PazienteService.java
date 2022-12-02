package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteService {

	public List<Paziente> listAll();

	public Paziente caricaSingoloElemento(Long id);

	public Paziente aggiorna(Paziente pazienteInstance);

	public Paziente inserisciNuovo(Paziente pazienteInstance);

	public void rimuovi(Long idToRemove);
	
	public void dimetti(Long id);
}
