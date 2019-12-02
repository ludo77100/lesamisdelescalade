package com.ludo.forms;

public class SpotForm {

	private String nom ;
	private String cotationMin ;
	private String localite ;
	public SpotForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpotForm(String nom, String cotationMin, String localite) {
		super();
		this.nom = nom;
		this.cotationMin = cotationMin;
		this.localite = localite;
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