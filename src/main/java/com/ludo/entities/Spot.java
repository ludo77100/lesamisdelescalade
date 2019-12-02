package com.ludo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY)
	private Collection<Secteur> secteur ;

	public Spot(@NotNull String nom, @NotNull String cotationMin, @NotNull String localite,
			Collection<Secteur> secteur) {
		super();
		this.nom = nom;
		this.cotationMin = cotationMin;
		this.localite = localite;
		this.secteur = secteur;
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
	
	public Collection<Secteur> getSecteur() {
		return secteur;
	}
	
	public void setSecteur(Collection<Secteur> secteur) {
		this.secteur = secteur;
	}
	
}
