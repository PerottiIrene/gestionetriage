package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteService {

	public List<Paziente> listAll();

	public Paziente caricaSingoloElemento(Long id);

	public void aggiorna(Paziente pazienteInstance);

	public void inserisciNuovo(Paziente pazienteInstance);

	public void rimuovi(Long idToRemove);
}
