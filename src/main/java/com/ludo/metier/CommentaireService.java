package com.ludo.metier;

import java.util.Date;

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
	
	public void saveCommentaire(Commentaire commentaire, Long spotId) {
		
		Commentaire newCommentaire = new Commentaire() ;
		Spot spotActuel = spotRepository.findById(spotId).get();
		Date date = new Date();

		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newCommentaire.setContenu(commentaire.getContenu());
		newCommentaire.setDateHeureCommentaire(date);
		newCommentaire.setUtilisateur(utilisateur);
		newCommentaire.setSpot(spotActuel);
		
		commentaireRepository.save(newCommentaire);
	}
	
	public void saveEditCommentaire(Commentaire commentaire, Long comId) {
		
		Commentaire comEdit = commentaireRepository.findById(comId).get();
		
		comEdit.setContenu(commentaire.getContenu());
		
		commentaireRepository.save(comEdit);
		
	}

}
