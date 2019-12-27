package com.ludo.forms;

import java.util.Date;


public class CommentaireForm {

	private Date dateHeureCommentaire = new Date() ; 
	private String commentaire ;
	public CommentaireForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentaireForm(Date dateHeureCommentaire, String commentaire) {
		super();
		this.dateHeureCommentaire = dateHeureCommentaire;
		this.commentaire = commentaire;
	}
	public Date getDateHeureCommentaire() {
		return dateHeureCommentaire;
	}
	public void setDateHeureCommentaire(Date dateHeureCommentaire) {
		this.dateHeureCommentaire = dateHeureCommentaire;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	
}
