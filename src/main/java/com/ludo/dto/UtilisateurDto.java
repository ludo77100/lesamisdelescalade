package com.ludo.dto;

/**
 * Couche DTO utilisateur pour l'application
 * @author A87671
 *
 */
public class UtilisateurDto {
	private String Pseudo ;
	private String motDePass ;
	private String email ;
	
	public UtilisateurDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UtilisateurDto(String pseudo, String motDePass, String email) {
		super();
		Pseudo = pseudo;
		this.motDePass = motDePass;
		this.email = email;
	}
	public String getPseudo() {
		return Pseudo;
	}
	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
	}
	public String getMotDePass() {
		return motDePass;
	}
	public void setMotDePass(String motDePass) {
		this.motDePass = motDePass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}