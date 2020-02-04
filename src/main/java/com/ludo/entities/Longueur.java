package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Longueur implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_longueur")
	private Long idLongueur ;
	@Column(name = "cotation", nullable = false, unique = false)
	@Size(min = 2, max = 2)
	private String cotation ;
	@Column(name = "longueurLong", nullable = false, unique = false)
	private Long longueurLong ;
	@Column(name = "nombre_points", nullable = false, unique = false)
	private int nombrePoints ;
	
	@ManyToOne
	@JoinColumn(name = "LONG_VOIE")
	private Voie voie ;

	public Longueur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Longueur(Long idLongueur, @Size(min = 2, max = 2) String cotation, Long longueurLong, int nombrePoints,
			Voie voie) {
		super();
		this.idLongueur = idLongueur;
		this.cotation = cotation;
		this.longueurLong = longueurLong;
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

	public Long getLongueurLong() {
		return longueurLong;
	}

	public void setLongueurLong(Long longueurLong) {
		this.longueurLong = longueurLong;
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