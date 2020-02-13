package com.ludo.dto;

public class UtilisateurDto {
	private String Pseudo ;
	private String motDePass ;
	private String email ;
	private String roles ;

	public UtilisateurDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UtilisateurDto(String pseudo, String motDePass, String email, String roles) {
		super();
		Pseudo = pseudo;
		this.motDePass = motDePass;
		this.email = email;
		this.setRoles(roles) ;
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

}
