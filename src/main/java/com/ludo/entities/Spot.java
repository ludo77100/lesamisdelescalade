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
	private String adresse ;
	@NotNull
	private String commune ;
	@NotNull
	private String region ;
	@NotNull
	private String departement ;
	
	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Spot(String adresse, String commune, String region, String departement, @NotNull String nom) {
		super();
		this.nom = nom ;
		this.adresse = adresse;
		this.commune = commune;
		this.region = region;
		this.departement = departement;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Long getIdSiteEscalade() {
		return idSiteEscalade;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
}
