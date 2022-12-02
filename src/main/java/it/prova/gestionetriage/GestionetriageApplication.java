package it.prova.gestionetriage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.Ruolo;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.service.PazienteService;
import it.prova.gestionetriage.service.RuoloService;
import it.prova.gestionetriage.service.UtenteService;

@SpringBootApplication
public class GestionetriageApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private PazienteService pazienteService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));

			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());

		}

		if (utenteServiceInstance.findByUsername("subOperator") == null) {
			Utente subOperator = new Utente("subOperator", "subOperator", "Antonio", "Verdi", new Date());
			subOperator.setEmail("u.user@prova.it");
			subOperator.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));

			utenteServiceInstance.inserisciNuovo(subOperator);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(subOperator.getId());

		}
		
		Paziente paziente1=new Paziente("mario","rossi","MRTAF4325",new Date());
		pazienteService.inserisciNuovo(paziente1);
		Paziente paziente2=new Paziente("irene","rossi","FDS457GF",new Date());
		pazienteService.inserisciNuovo(paziente2);
		Paziente paziente3=new Paziente("luca","rossi","GDAR7E572",new Date());
		pazienteService.inserisciNuovo(paziente3);

	}

}
