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

/**
 * Couche entities reservation pour l'application
 * @author A87671
 *
 */
@Entity
public class Reservation implements Serializable{

	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id de la réservation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_reservation")
	private Long idReservation ;
	
	/**
	 * reservant de la réservation
	 */
	@Column(name = "reservant", nullable = false, unique = false)
	private String reservant ;
	
	/**
	 * propriétaire à qui la reservation est demandé
	 */
	@Column(name = "proprietaire", nullable = false, unique = false)
	private String proprietaire ;
	
	/**
	 * date de la demande de réservatioon
	 */
	@Column(name = "dateDemande", nullable = false, unique = false)
	private Date dateDemande ;
	
	/**
	 * état de la damande
	 */
	@Column(name = "etatDemande", nullable = false, unique = false)
	private boolean etatDemande ;
	
	/**
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	
	/**
	 * Relation avec la table Topo
	 */
	@OneToOne(cascade = CascadeType.DETACH)
	private Topo topo ;

	/**
	 * instanciation de reservation
	 */
	public Reservation() {
		super();
	}

	/**
	 *  instanciation de reservation
	 * @param idReservation id de la réservation
	 * @param reservant demander de la réservation
	 * @param proprietaire receveur de la réservation 
	 * @param dateDemande date de la demande de réservation
	 * @param etatDemande état de la demande de réservation
	 * @param utilisateur utilisateur qui fait la demande
	 * @param topo topo pour lequel la demande de réservation est fait
	 */
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
