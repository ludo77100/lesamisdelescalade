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

/**
 * Couche entities voie pour l'application
 * @author A87671
 *
 */
@Entity
public class Voie implements Serializable {
	
	/**
	 * Constant serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id de la voie
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_voie")
	private Long idVoie ;
	
	/**
	 * nom de la voie
	 */
	@Column(name = "nom_voie", nullable = false, unique = false)
	@Length(min = 4, max = 35)
	private String nomVoie ;
	
	/**
	 * cotation de la voie
	 */
	@Column(name = "cotation", nullable = false, unique = false)
	@Length(min = 2, max = 2)
	private String cotation ;
	
	/**
	 * longueur de la voie
	 */
	@Column(name = "longueurVoie", nullable = false, unique = false)
	private Double longueurVoie ;
	
	/**
	 * si voie equipée ou non
	 */
	@Column(name = "equipee", nullable = false, unique = false)
	private String equipee ;
	
	/**
	 * Relation avec la table secteur
	 */
	@ManyToOne
	@JoinColumn(name = "VOIE_SECT")
	private Secteur secteur ;
	
	/**
	 * Relation avec la table longueur
	 */
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Longueur> longueur;
	
	/**
	 * instanciation de voie
	 */
	public Voie() {
		super();
	}
	
	/**
	 * instanciation de voie
	 * @param idVoie id de la voie
	 * @param nomVoie nom de la voie
	 * @param cotation cotation de la voie
	 * @param longueurVoie longueur de la voie 
	 * @param equipee si voie equipée ou non
	 * @param secteur secteur de la voie
	 * @param longueur longueurs de la voie
	 */
	public Voie(Long idVoie, @Length(min = 4, max = 35) String nomVoie, @Length(min = 2, max = 2) String cotation,
			Double longueurVoie, String equipee, Secteur secteur, Collection<Longueur> longueur) {
		super();
		this.idVoie = idVoie;
		this.nomVoie = nomVoie;
		this.cotation = cotation;
		this.longueurVoie = longueurVoie;
		this.equipee = equipee;
		this.secteur = secteur;
		this.longueur = longueur;
	}
	public Long getIdVoie() {
		return idVoie;
	}
	public void setIdVoie(Long idVoie) {
		this.idVoie = idVoie;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public String getCotation() {
		return cotation;
	}
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	public Double getLongueurVoie() {
		return longueurVoie;
	}
	public void setLongueurVoie(Double longueurVoie) {
		this.longueurVoie = longueurVoie;
	}
	public String getEquipee() {
		return equipee;
	}
	public void setEquipee(String equipee) {
		this.equipee = equipee;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Collection<Longueur> getLongueur() {
		return longueur;
	}
	public void setLongueur(Collection<Longueur> longueur) {
		this.longueur = longueur;
	}
}
