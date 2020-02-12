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

import org.hibernate.validator.constraints.Length;

@Entity
public class Spot implements Serializable{
	
	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id du spot
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_site_escalade")
	private Long idSiteEscalade ;
	
	/**
	 * nom du spot
	 */
	@Column(name = "nom", nullable = false, unique = false)
	@Length(min = 2, max = 35)
	private String nom ;
	
	/**
	 * localité du spot
	 */
	@Column(name = "localite", nullable = false, unique = false)
	@Length(min = 2, max = 255)
	private String localite ;
	
	/**
	 * tag officiel du spot
	 */
	@Column(name = "officiel", nullable = false, unique = false)
	private boolean officiel ;
	
	/**
	 * Relation avec la table Secteur
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Secteur> secteur ;
	
	/**
	 * Relation avec la table commentaire
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Commentaire> commentaire;
	
	/**
	 * Relation avec la table Topo
	 */
	@OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Topo> topo ;
	
	/**
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;

	/**
	 * instanciation de spot
	 */
	public Spot() {
		super();
	}

	/**
	 * instanciation de spot
	 * @param idSiteEscalade id du spot
	 * @param nom nom du spot
	 * @param localite localité du spot
	 * @param officiel tag officiel du spot
	 * @param secteur secteurs du spot 
	 * @param commentaire commentaires du spot
	 * @param topo topos du spot
	 * @param utilisateur utilisateur du spot
	 */
	public Spot(Long idSiteEscalade, String nom, String localite, boolean officiel,
			Collection<Secteur> secteur, Collection<Commentaire> commentaire, Collection<Topo> topo,
			Utilisateur utilisateur) {
		super();
		this.idSiteEscalade = idSiteEscalade;
		this.nom = nom;
		this.localite = localite;
		this.officiel = officiel;
		this.secteur = secteur;
		this.commentaire = commentaire;
		this.topo = topo;
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


	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public boolean isOfficiel() {
		return officiel;
	}

	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
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

	public Collection<Topo> getTopo() {
		return topo;
	}

	public void setTopo(Collection<Topo> topo) {
		this.topo = topo;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
