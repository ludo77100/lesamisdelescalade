package com.ludo.forms;

public class SecteurForm {
	
	private Long idSecteur ;
	private String acces ;
	private String localisation ;
	private String nomSecteur ;
	private int nombreVoies ;
	private String typeRoche ;
	
	
	public SecteurForm(Long idSecteur, String acces, String localisation, String nomSecteur, int nombreVoies,
			String typeRoche) {
		super();
		this.idSecteur = idSecteur;
		this.acces = acces;
		this.localisation = localisation;
		this.nomSecteur = nomSecteur;
		this.nombreVoies = nombreVoies;
		this.typeRoche = typeRoche;
	}


	
	public SecteurForm() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getIdSecteur() {
		return idSecteur;
	}


	public void setIdSecteur(Long idSecteur) {
		this.idSecteur = idSecteur;
	}


	public String getAcces() {
		return acces;
	}


	public void setAcces(String acces) {
		this.acces = acces;
	}


	public String getLocalisation() {
		return localisation;
	}


	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}


	public String getNomSecteur() {
		return nomSecteur;
	}


	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}


	public int getNombreVoies() {
		return nombreVoies;
	}


	public void setNombreVoies(int nombreVoies) {
		this.nombreVoies = nombreVoies;
	}


	public String getTypeRoche() {
		return typeRoche;
	}


	public void setTypeRoche(String typeRoche) {
		this.typeRoche = typeRoche;
	}

	
	
}
