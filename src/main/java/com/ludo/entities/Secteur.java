package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Secteur implements Serializable {
	@Id
	@GeneratedValue
	private Long idSecteur;
	@NotNull
	private String nomSecteur ;
	@NotNull
	private String acces ;
	@NotNull
	private String typeRoche ;
	@NotNull
	private int nombreVoies ;
	@NotNull
	private String cotationMin ;
	
	public Secteur(@NotNull String nomSecteur, @NotNull String acces, @NotNull String typeRoche,
			@NotNull int nombreVoies, @NotNull String cotationMin) {
		super();
		this.nomSecteur = nomSecteur;
		this.acces = acces;
		this.typeRoche = typeRoche;
		this.nombreVoies = nombreVoies;
		this.cotationMin = cotationMin;
	}

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdSecteur() {
		return idSecteur;
	}

	public void setIdSecteur(Long idSecteur) {
		this.idSecteur = idSecteur;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
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
	
	
}
