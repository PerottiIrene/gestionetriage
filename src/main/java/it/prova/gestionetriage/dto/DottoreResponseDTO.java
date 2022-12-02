package it.prova.gestionetriage.dto;

public class DottoreResponseDTO {
	
	private String nome;
	private String cognome;
	private String codiceDottore;
	private boolean inServizio;
	private boolean inVisita;
	
	public DottoreResponseDTO() {
		super();
	}

	public DottoreResponseDTO(String nome, String cognome, String codiceDottore, boolean inServizio, boolean inVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
		this.inServizio = inServizio;
		this.inVisita = inVisita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}

	public boolean isInServizio() {
		return inServizio;
	}

	public void setInServizio(boolean inServizio) {
		this.inServizio = inServizio;
	}

	public boolean isInVisita() {
		return inVisita;
	}

	public void setInVisita(boolean inVisita) {
		this.inVisita = inVisita;
	}
	
	

}
