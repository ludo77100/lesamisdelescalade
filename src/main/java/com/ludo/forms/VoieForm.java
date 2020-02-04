package com.ludo.forms;

public class VoieForm {

	private String nomVoie ;
	private String cotation ;
	private Double longueurVoie ;
	private String equipee ;
	public VoieForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoieForm(String nomVoie, String cotation, Double longueurVoie, String equipee) {
		super();
		this.nomVoie = nomVoie;
		this.cotation = cotation;
		this.longueurVoie = longueurVoie;
		this.equipee = equipee;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public String getCotation() {
		return cotation;
	}
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	public Double getLongueurVoie() {
		return longueurVoie;
	}
	public void setLongueurVoie(Double longueurVoie) {
		this.longueurVoie = longueurVoie;
	}
	public String getEquipee() {
		return equipee;
	}
	public void setEquipee(String equipee) {
		this.equipee = equipee;
	}
	
	
	
}
