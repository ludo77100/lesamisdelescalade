package com.ludo.forms;

import java.util.Date;

public class TopoForm {

	private String nom ;
	private String dateParution ;
	private String description ;
	private String lieu ;
	
	public TopoForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopoForm(String nom, String dateParution, String description, String lieu) {
		super();
		this.nom = nom;
		this.dateParution = dateParution;
		this.description = description;
		this.lieu = lieu;
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
	
	
	
}
