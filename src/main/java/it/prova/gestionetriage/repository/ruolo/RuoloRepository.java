package it.prova.gestionetriage.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetriage.model.Ruolo;



public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
