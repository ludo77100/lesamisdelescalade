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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_voie")
	private Long idVoie ;
	
	@Column(name = "nom_voie", nullable = false, unique = false)
	@Length(min = 4, max = 35)
	private String nomVoie ;
	
	@Column(name = "cotation", nullable = false, unique = false)
	@Length(min = 2, max = 2)
	private String cotation ;
	
	@Column(name = "longueurVoie", nullable = false, unique = false)
	private Double longueurVoie ;
	
	@Column(name = "equipee", nullable = false, unique = false)
	private String equipee ;
	
	@ManyToOne
	@JoinColumn(name = "VOIE_SECT")
	private Secteur secteur ;
	
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Longueur> longueur;
	
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
