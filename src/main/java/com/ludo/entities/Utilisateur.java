package com.ludo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur implements Serializable {
	@Id
	@GeneratedValue
	private Long idUtilisateur;
	private String Pseudo ;
	private String motDePass ;
	private String email ;
	private String role ;
	
	/*
	 * Relation avec la table Spot  
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Spot> spot ;
	
	/*
	 * Relation avec la table Commentaire
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Commentaire>commentaire ;
	
	/*
	 * Relation avec la table Topo
	 */
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
	private Collection<Topo> topo ;
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utilisateur(Long idUtilisateur, String pseudo, String motDePass, String email, String role) {
		super();
		this.idUtilisateur = idUtilisateur;
		Pseudo = pseudo;
		this.motDePass = motDePass;
		this.email = email;
		this.role = role;
	}
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
