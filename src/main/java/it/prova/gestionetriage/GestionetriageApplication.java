package it.prova.gestionetriage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionetriage.service.RuoloService;
import it.prova.gestionetriage.service.UtenteService;

@SpringBootApplication
public class GestionetriageApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionetriageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
