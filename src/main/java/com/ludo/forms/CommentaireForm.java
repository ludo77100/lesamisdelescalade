package com.ludo.forms;

import java.util.Date;


public class CommentaireForm {

	private Date dateHeureCommentaire = new Date() ; 
	private String contenu ;
	private Long idCommentaire ;
	
	public CommentaireForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommentaireForm(Date dateHeureCommentaire, String contenu, Long idCommentaire) {
		super();
		this.dateHeureCommentaire = dateHeureCommentaire;
		this.contenu = contenu;
		this.idCommentaire = idCommentaire;
	}
	
	public Date getDateHeureCommentaire() {
		return dateHeureCommentaire;
	}
	
	public void setDateHeureCommentaire(Date dateHeureCommentaire) {
		this.dateHeureCommentaire = dateHeureCommentaire;
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public Long getIdCommentaire() {
		return idCommentaire;
	}
	
	public void setIdCommentaire(Long idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	
	
	
}
