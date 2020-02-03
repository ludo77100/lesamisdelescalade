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

import org.hibernate.validator.constraints.Length;
@Entity
public class Voie implements Serializable {
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
	@Column(name = "longeur_min", nullable = false, unique = false)
	private  Double longeurMin ;
	@Column(name = "longueur_max", nullable = false, unique = false)
	private Double longueurMax ;
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
	public Voie(Long idVoie, @NotNull String nomVoie, @NotNull String cotation, 
			@NotNull Double longeurMin, @NotNull Double longueurMax, @NotNull String equipee, Secteur secteur,
			Collection<Longueur> longueur) {
		super();
		this.idVoie = idVoie;
		this.nomVoie = nomVoie;
		this.cotation = cotation;
		this.longeurMin = longeurMin;
		this.longueurMax = longueurMax;
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
	public Double getLongeurMin() {
		return longeurMin;
	}
	public void setLongeurMin(Double longeurMin) {
		this.longeurMin = longeurMin;
	}
	public Double getLongueurMax() {
		return longueurMax;
	}
	public void setLongueurMax(Double longueurMax) {
		this.longueurMax = longueurMax;
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
