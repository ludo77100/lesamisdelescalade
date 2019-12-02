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
	private String nomSecteur ;
	@NotNull
	private String localisation ;
	@NotNull
	private String acces ;
	@NotNull
	private  String typeRoche ;
	@NotNull
	private int nombreVoies ;
	@NotNull
	private String cotationMin ;
	@ManyToOne
	@JoinColumn(name = "VOIE_SECT")
	private Secteur secteur ;
	@OneToMany(mappedBy = "voie", fetch = FetchType.LAZY)
	private Collection<Longueur> longueur;
	
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voie(@NotNull String nomSecteur, @NotNull String localisation, @NotNull String acces,
			@NotNull String typeRoche, @NotNull int nombreVoies, @NotNull String cotationMin, Secteur secteur) {
		super();
		this.nomSecteur = nomSecteur;
		this.localisation = localisation;
		this.acces = acces;
		this.typeRoche = typeRoche;
		this.nombreVoies = nombreVoies;
		this.cotationMin = cotationMin;
		this.secteur = secteur;
	}
	public Long getIdVoie() {
		return idVoie;
	}
	public void setIdVoie(Long idVoie) {
		this.idVoie = idVoie;
	}
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getAcces() {
		return acces;
	}
	public void setAcces(String acces) {
		this.acces = acces;
	}
	public String getTypeRoche() {
		return typeRoche;
	}
	public void setTypeRoche(String typeRoche) {
		this.typeRoche = typeRoche;
	}
	public int getNombreVoies() {
		return nombreVoies;
	}
	public void setNombreVoies(int nombreVoies) {
		this.nombreVoies = nombreVoies;
	}
	public String getCotationMin() {
		return cotationMin;
	}
	public void setCotationMin(String cotationMin) {
		this.cotationMin = cotationMin;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
	
}
