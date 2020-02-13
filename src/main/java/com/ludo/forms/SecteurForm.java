package com.ludo.forms;

public class SecteurForm {
	
	private Long idSecteur ;
	private String localisation ;
	private String nomSecteur ;
	private String typeRoche ;
	
	
	public SecteurForm(Long idSecteur, String localisation, String nomSecteur, String typeRoche) {
		super();
		this.idSecteur = idSecteur;
		this.localisation = localisation;
		this.nomSecteur = nomSecteur;
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

	public String getTypeRoche() {
		return typeRoche;
	}


	public void setTypeRoche(String typeRoche) {
		this.typeRoche = typeRoche;
	}

	
	
}
