package it.prova.gestionetriage.dto;

public class DottoreRequestDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	
	public DottoreRequestDTO() {}

	public DottoreRequestDTO(Long id, String nome, String cognome, String codiceDottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	

}
