package com.ludo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

/**
 * Couche entities commentaire pour l'application
 * @author A87671
 *
 */
@Entity
public class Commentaire implements Serializable {
	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *id du secteur
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id_commentaire")
	private Long idCommentaire ;
	
	/*
	 * date et heure du commentaire
	 */
	@Column(name = "date_heure_commentaire", nullable = false, unique = false)
	private Date dateHeureCommentaire ; 
	
	/**
	 * contenu du commentaire
	 */
	@Column(name = "contenu", nullable = false, unique = false)
	@Length(min = 2, max = 255)
	private String contenu ;
	
	/**
	 * Relation avec la table Spot
	 */
	@ManyToOne
	@JoinColumn(name = "SPOTID")
	private Spot spot ;
	
	/**
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	/**
	 * instanciation du secteur
	 */
	public Commentaire() {
		super();
	}

	/**
	 * instaciation du secteur
	 * @param idCommentaire id du commentaire
	 * @param dateHeureCommentaire date et heure du commentaire 
	 * @param contenu contenu du commentaire 
	 * @param spot spot auquel est lié le commentaire
	 * @param utilisateur utilisateur qui a posté le commentaire
	 */
	public Commentaire(Long idCommentaire, Date dateHeureCommentaire, @Length(min = 2, max = 255) String contenu,
			Spot spot, Utilisateur utilisateur) {
		super();
		this.idCommentaire = idCommentaire;
		this.dateHeureCommentaire = dateHeureCommentaire;
		this.contenu = contenu;
		this.spot = spot;
		this.utilisateur = utilisateur;
	}

	public Long getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(Long idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public Date getDateHeureCommentaire() {
		return dateHeureCommentaire;
	}

	public void setDateHeureCommentaire(Date dateHeureCommentaire) {
		this.dateHeureCommentaire = dateHeureCommentaire;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
