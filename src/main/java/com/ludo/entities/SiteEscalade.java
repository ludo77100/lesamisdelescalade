package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SiteEscalade implements Serializable{
	@Id @GeneratedValue
	private Long idSiteEscalade ;
	private String adresse ;
	private String commune ;
	private String region ;
	private String departement ;
	public SiteEscalade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SiteEscalade(Long idSite, String adresse, String commune, String region, String departement) {
		super();
		this.idSiteEscalade = idSite;
		this.adresse = adresse;
		this.commune = commune;
		this.region = region;
		this.departement = departement;
	}
	public Long getIdSite() {
		return idSiteEscalade;
	}
	public void setIdSite(Long idSite) {
		this.idSiteEscalade = idSite;
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
