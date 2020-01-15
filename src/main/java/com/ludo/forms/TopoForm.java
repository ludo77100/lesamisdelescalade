package com.ludo.forms;



public class TopoForm {

	private String nom ;
	private String dateParution ;
	private String description ;
	private String lieu ;
	private String spotNom ;
	public TopoForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TopoForm(String nom, String dateParution, String description, String lieu, String spotNom) {
		super();
		this.nom = nom;
		this.dateParution = dateParution;
		this.description = description;
		this.lieu = lieu;
		this.spotNom = spotNom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDateParution() {
		return dateParution;
	}
	public void setDateParution(String dateParution) {
		this.dateParution = dateParution;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getSpotNom() {
		return spotNom;
	}
	public void setSpotNom(String spotNom) {
		this.spotNom = spotNom;
	}
	
	
	
}
