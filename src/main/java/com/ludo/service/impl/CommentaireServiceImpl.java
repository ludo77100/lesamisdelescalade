package com.ludo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
import com.ludo.service.CommentaireService;

@Service
@Transactional
public class CommentaireServiceImpl implements CommentaireService{

	@Autowired
	UtilisateurRepository utilisateurRepository ;
	@Autowired
	CommentaireRepository commentaireRepository ;
	@Autowired
	SpotRepository spotRepository ;

	@Override
	public void saveCommentaire(@Valid Commentaire commentaire, Long spotId) {
		
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

	@Override
	public void saveEditCommentaire(@Valid Commentaire commentaire, Long comId) {
		
		Commentaire comEdit = commentaireRepository.findById(comId).get();
		comEdit.setContenu(commentaire.getContenu());		
		commentaireRepository.save(comEdit);
	}

	@Override
	public void deleteById(Long comId) {
		commentaireRepository.deleteById(comId);
		
	}

	@Override
	public Optional<Commentaire> findById(Long comId) {
		Optional<Commentaire> commentaire = commentaireRepository.findById(comId);
		return commentaire;
	}

	@Override
	public List<Commentaire> findCommentaireBySpot(Long spotId) {
		List<Commentaire> commentaires = commentaireRepository.findCommentaireBySpot(spotId);
		return commentaires ;
	}

}
