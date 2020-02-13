package com.ludo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Reservation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_reservation")
	private Long idReservation ;
	@Column(name = "reservant", nullable = false, unique = false)
	private String reservant ;
	@Column(name = "proprietaire", nullable = false, unique = false)
	private String proprietaire ;
	@Column(name = "dateDemande", nullable = false, unique = false)
	private Date dateDemande ;
	@Column(name = "etatDemande", nullable = false, unique = false)
	private boolean etatDemande ;
	
	/*
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	
	/*
	 * Relation avec la table Topo
	 */
	@OneToOne(cascade = CascadeType.DETACH)
	private Topo topo ;


	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(Long idReservation, String reservant, String proprietaire, Date dateDemande, boolean etatDemande,
			Utilisateur utilisateur, Topo topo) {
		super();
		this.idReservation = idReservation;
		this.reservant = reservant;
		this.proprietaire = proprietaire;
		this.dateDemande = dateDemande;
		this.etatDemande = etatDemande;
		this.utilisateur = utilisateur;
		this.topo = topo;
	}


	public Long getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}


	public String getReservant() {
		return reservant;
	}


	public void setReservant(String reservant) {
		this.reservant = reservant;
	}


	public String getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}


	public Date getDateDemande() {
		return dateDemande;
	}


	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}


	public boolean isEtatDemande() {
		return etatDemande;
	}


	public void setEtatDemande(boolean etatDemande) {
		this.etatDemande = etatDemande;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Topo getTopo() {
		return topo;
	}


	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	
}
