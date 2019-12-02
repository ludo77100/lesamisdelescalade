package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Longueur implements Serializable {
	@Id @GeneratedValue
	private Long idLongueur ;
	@NotNull
	private String cotation ;
	@NotNull
	private int nombrePoints ;
	@ManyToOne
	@JoinColumn(name = "LONG_VOIE")
	private Voie voie ;
	
	public Longueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Longueur(@NotNull String cotation, @NotNull int nombrePoints, Voie voie) {
		super();
		this.cotation = cotation;
		this.nombrePoints = nombrePoints;
		this.voie = voie;
	}
	public Long getIdLongueur() {
		return idLongueur;
	}
	public void setIdLongueur(Long idLongueur) {
		this.idLongueur = idLongueur;
	}
	public String getCotation() {
		return cotation;
	}
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	public int getNombrePoints() {
		return nombrePoints;
	}
	public void setNombrePoints(int nombrePoints) {
		this.nombrePoints = nombrePoints;
	}
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
	}
}
