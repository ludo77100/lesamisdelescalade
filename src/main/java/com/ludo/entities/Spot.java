package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Spot implements Serializable{
	@Id @GeneratedValue
	private Long idSiteEscalade ;
	@NotNull
	private String nom ;
	@NotNull
	private String cotationMin ;
	@NotNull
	private String localite ;
	public Spot(@NotNull String nom, @NotNull String cotationMin, @NotNull String localite) {
		super();
		this.nom = nom;
		this.cotationMin = cotationMin;
		this.localite = localite;
	}
	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdSiteEscalade() {
		return idSiteEscalade;
	}
	public void setIdSiteEscalade(Long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCotationMin() {
		return cotationMin;
	}
	public void setCotationMin(String cotationMin) {
		this.cotationMin = cotationMin;
	}
	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
	
	
}
