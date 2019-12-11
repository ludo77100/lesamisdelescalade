package com.ludo.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire implements Serializable {
	@Id @GeneratedValue
	private Long idCommentaire ;
	private Date dateHeureCommentaire ; 
	private String commentaireCat ; /*news ou spot ou ...*/
	private String commentaire ;
	
	/*
	 * Relation avec la table Spot
	 */
	@ManyToOne
	@JoinColumn(name = "SPOTID")
	private Spot spot ;
	
	/*
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	public Commentaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commentaire(Long idCommentaire, Date dateHeureCommentaire, String commentaireCat, String commentaire,
			Spot spot, Utilisateur utilisateur) {
		super();
		this.idCommentaire = idCommentaire;
		this.dateHeureCommentaire = dateHeureCommentaire;
		this.commentaireCat = commentaireCat;
		this.commentaire = commentaire;
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

	public String getCommentaireCat() {
		return commentaireCat;
	}

	public void setCommentaireCat(String commentaireCat) {
		this.commentaireCat = commentaireCat;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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
