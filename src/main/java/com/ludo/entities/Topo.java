package com.ludo.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Topo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_topo")
	private Long idTopo;
	@Column(name = "nom", nullable = false, unique = false)
	private String nom ;
	@Column(name = "reserve", nullable = false, unique = false)
	private boolean reserve ;
	@Column(name = "date_parution", nullable = false, unique = false)
	private Date dateParution ;
	@Column(name = "description", nullable = false, unique = false)
	private String description ;
	@Column(name = "reserve_par", nullable = true, unique = false)
	private String reservePar ;
	
	/*
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;
	
	/*
	 * Relation avec la table Spot
	 */
	@ManyToOne
	@JoinColumn(name = "SPOTID")
	private Spot spot ;

	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topo(Long idTopo, String nom, boolean reserve, Date dateParution, String description, String reservePar,
			Utilisateur utilisateur, Spot spot) {
		super();
		this.idTopo = idTopo;
		this.nom = nom;
		this.reserve = reserve;
		this.dateParution = dateParution;
		this.description = description;
		this.reservePar = reservePar;
		this.utilisateur = utilisateur;
		this.spot = spot;
	}

	public Long getIdTopo() {
		return idTopo;
	}

	public void setIdTopo(Long idTopo) {
		this.idTopo = idTopo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isReserve() {
		return reserve;
	}

	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReservePar() {
		return reservePar;
	}

	public void setReservePar(String reservePar) {
		this.reservePar = reservePar;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}
	
	
}
