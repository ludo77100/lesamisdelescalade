package com.ludo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Entity
public class Voie implements Serializable {
	@Id @GeneratedValue
	private Long idVoie ;
	@NotNull
	private String nomVoie ;
	@NotNull
	private String cotation ;
	@NotNull
	private int nombrePointVoie ;
	@NotNull
	private  Double longeurMin ;
	@NotNull
	private Double longueurMax ;
	@NotNull
	private String equipee ;
	@ManyToOne
	@JoinColumn(name = "VOIE_SECT")
	private Secteur secteur ;
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY)
	private Collection<Longueur> longueur;
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voie(Long idVoie, @NotNull String nomVoie, @NotNull String cotation, @NotNull int nombrePointVoie,
			@NotNull Double longeurMin, @NotNull Double longueurMax, @NotNull String equipee, Secteur secteur,
			Collection<Longueur> longueur) {
		super();
		this.idVoie = idVoie;
		this.nomVoie = nomVoie;
		this.cotation = cotation;
		this.nombrePointVoie = nombrePointVoie;
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
	public int getNombrePointVoie() {
		return nombrePointVoie;
	}
	public void setNombrePointVoie(int nombrePointVoie) {
		this.nombrePointVoie = nombrePointVoie;
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
