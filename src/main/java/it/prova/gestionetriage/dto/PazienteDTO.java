package it.prova.gestionetriage.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	private Date dataRegistrazione;
	private String codiceDottore;
	private StatoPaziente stato;

	public PazienteDTO() {}

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione,
			String codiceDottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceDottore = codiceDottore;
	}
	
	public PazienteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") String codiceFiscale, Date dataRegistrazione,
			String codiceDottore, StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.codiceDottore = codiceDottore;
		this.stato = stato;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getCodiceDottore() {
		return codiceDottore;
	}

	public void setCodiceDottore(String codiceDottore) {
		this.codiceDottore = codiceDottore;
	}
	
	public StatoPaziente getStato() {
		return stato;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}

	public Paziente buildPazienteModel() {
		Paziente result= new Paziente(this.id,  this.nome, this.cognome,this.codiceFiscale,this.dataRegistrazione,this.codiceDottore,this.stato);
			
		return result;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(),pazienteModel.getNome(), pazienteModel.getCognome(),pazienteModel.getCodiceFiscale(), 
				pazienteModel.getDataRegistrazione(),pazienteModel.getCodiceDottore(),pazienteModel.getStato());
		
		return result;
	}

	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
			
			return result;
		}).collect(Collectors.toList());
	}
	
	
}
