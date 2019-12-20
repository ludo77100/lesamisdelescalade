package com.ludo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Spot implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_site_escalade")
	private Long idSiteEscalade ;
	
	@Column(name = "nom", nullable = false, unique = false)
	private String nom ;
	
	@Column(name = "cotation_min", nullable = false, unique = false)
	private String cotationMin ;
	
	@Column(name = "localite", nullable = false, unique = false)
	private String localite ;
	
	/*
	 * Relation avec la table Secteur
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Secteur> secteur ;
	
	/*
	 * Relation avec la table commentaire
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY)
	private Collection<Commentaire> commentaire;
	
	/*
	 * Relation avec la table Topo
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY)
	private Collection<Topo> topo ;
	
	/*
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Spot(Long idSiteEscalade, @NotNull String nom, @NotNull String cotationMin, @NotNull String localite,
			Collection<Secteur> secteur, Collection<Commentaire> commentaire, Utilisateur utilisateur) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nom = nom;
		this.cotationMin = cotationMin;
		this.localite = localite;
		this.secteur = secteur;
		this.commentaire = commentaire;
		this.utilisateur = utilisateur;
	}

	public Long getIdSiteEscalade() {
		return idSiteEscalade;
	}

	public void setIdSiteEscalade(Long idSiteEscalade) {
		this.idSiteEscalade = idSiteEscalade;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCotationMin() {
		return cotationMin;
	}

	public void setCotationMin(String cotationMin) {
		this.cotationMin = cotationMin;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public Collection<Secteur> getSecteur() {
		return secteur;
	}

	public void setSecteur(Collection<Secteur> secteur) {
		this.secteur = secteur;
	}

	public Collection<Commentaire> getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Collection<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
}
