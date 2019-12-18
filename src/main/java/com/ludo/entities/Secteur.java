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
public class Secteur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_secteur")
	private Long idSecteur;
	@Column(name = "nom_secteur", nullable = false, unique = false)
	private String nomSecteur;
	@Column(name = "acces", nullable = false, unique = false)
	private String acces;
	@Column(name = "type_roche", nullable = false, unique = false)
	private String typeRoche;
	@Column(name = "localisation", nullable = false, unique = false)
	private String localisation;
	@Column(name = "nombre_voies", nullable = false, unique = false)
	private int nombreVoies;
	
	/*
	 * Relation avec la table Spot
	 */
	@ManyToOne
	@JoinColumn(name = "SPOTID")
	private Spot spot;
	
	/*
	 * Relation ave la table Voie
	 */
	@OneToMany(mappedBy = "secteur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Voie> voie;

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secteur(Long idSecteur, String nomSecteur, String acces, String typeRoche, String localisation,
			int nombreVoies, Spot spot, Collection<Voie> voie) {
		super();
		this.idSecteur = idSecteur;
		this.nomSecteur = nomSecteur;
		this.acces = acces;
		this.typeRoche = typeRoche;
		this.localisation = localisation;
		this.nombreVoies = nombreVoies;
		this.spot = spot;
		this.voie = voie;
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

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getNombreVoies() {
		return nombreVoies;
	}

	public void setNombreVoies(int nombreVoies) {
		this.nombreVoies = nombreVoies;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Collection<Voie> getVoie() {
		return voie;
	}

	public void setVoie(Collection<Voie> voie) {
		this.voie = voie;
	}


}
