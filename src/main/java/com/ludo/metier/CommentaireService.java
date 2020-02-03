package com.ludo.metier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.CommentaireRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Commentaire;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.CommentaireForm;

@Service
public class CommentaireService {
	
	@Autowired
	UtilisateurRepository utilisateurRepository ;
	@Autowired
	CommentaireRepository commentaireRepository ;
	@Autowired
	SpotRepository spotRepository ;
	public void saveCommentaire(CommentaireForm commentaireForm, Long spotId) {
		Commentaire newCommentaire = new Commentaire() ;
		Spot spotActuel = spotRepository.findById(spotId).get();

		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newCommentaire.setCommentaire(commentaireForm.getCommentaire());
		newCommentaire.setDateHeureCommentaire(commentaireForm.getDateHeureCommentaire());
		newCommentaire.setUtilisateur(utilisateur);
		newCommentaire.setSpot(spotActuel);
		
		commentaireRepository.save(newCommentaire);
	}
	
	public void saveEditCommentaire(CommentaireForm commentaireForm, Long comId) {
		
		Commentaire comEdit = commentaireRepository.findById(comId).get();
		
		comEdit.setCommentaire(commentaireForm.getCommentaire());
		
		commentaireRepository.save(comEdit);
		
	}

}
