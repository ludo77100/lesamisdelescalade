package com.ludo.forms;

public class SpotForm {

	private String nom ;
	private String localite ;
	public SpotForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpotForm(String nom, String localite) {
		super();
		this.nom = nom;
		this.localite = localite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	
}