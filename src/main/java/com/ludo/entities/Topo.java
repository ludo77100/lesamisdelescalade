package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

/**
 * Couche entities topo pour l'application
 * @author A87671
 *
 */
@Entity
public class Topo implements Serializable {
	
	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id du topo
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_topo")
	private Long idTopo;
	
	/**
	 * nom du topo
	 */
	@Column(name = "nom", nullable = false, unique = false)
	@Length(min = 2, max = 35)
	private String nom ;
	
	/**
	 * disponibilté du topo
	 */
	@Column(name = "disponible", nullable = false, unique = false)
	private boolean disponible ;
	
	/**
	 * date de parution du topo
	 */
	@Column(name = "date_parution", nullable = false, unique = false)
	private String dateParution ;
	
	/**
	 * description du topo
	 */
	@Column(name = "description", nullable = false, unique = false)
	@Length(min = 2, max = 35)
	private String description ;
	
	/**
	 * spot du topo
	 */
	@Column(name = "spot_nom", nullable = false, unique = false)
	@Length(min = 2, max = 255)
	private String spotNom ;
	
	/**
	 * lieu du topo
	 */
	@Column(name = "lieu", nullable = false, unique = false)
	@Length(min = 2, max = 35)
	private String lieu ;

	/**
	 * Relation avec la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEURID")
	private Utilisateur utilisateur ;
	
	/**
	 * Relation avec la table Spot
	 */
	@ManyToOne
	@JoinColumn(name = "SPOTID")
	private Spot spot ;
	
	/**
	 * instanciation de topo
	 */
	public Topo() {
		super();
	}

	/**
	 * instanciation de topo
	 * @param idTopo id du topo
	 * @param nom nom du topo
	 * @param disponible disponibilité du topo
	 * @param dateParution date de parution du topo
	 * @param description description du topo
	 * @param spotNom spot auquel est lié le topo
	 * @param lieu lieu du topo
	 * @param utilisateur utilisateur du topo
	 * @param spot spot du topo
	 */
	public Topo(Long idTopo, String nom, boolean disponible, String dateParution, String description, String spotNom,
			String lieu, Utilisateur utilisateur, Spot spot) {
		super();
		this.idTopo = idTopo;
		this.nom = nom;
		this.disponible = disponible;
		this.dateParution = dateParution;
		this.description = description;
		this.spotNom = spotNom;
		this.lieu = lieu;
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getDateParution() {
		return dateParution;
	}

	public void setDateParution(String dateParution) {
		this.dateParution = dateParution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpotNom() {
		return spotNom;
	}

	public void setSpotNom(String spotNom) {
		this.spotNom = spotNom;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
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
