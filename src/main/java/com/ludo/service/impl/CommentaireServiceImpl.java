package com.ludo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

/**
 * Implémentation Service commentaire pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class CommentaireServiceImpl implements CommentaireService{

	@Autowired
	UtilisateurRepository utilisateurRepository ;
	@Autowired
	CommentaireRepository commentaireRepository ;
	@Autowired
	SpotRepository spotRepository ;

	/**
	 * Pour sauvegarder un nouveau commentaire
	 * @param commentaire commentaire à sauvegarder
	 * @param spotId id du spot auquel est lié le commentaire
	 */
	@Override
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

	/**
	 * Pour sauvegarder un commentaire édité
	 * @param commentaire commentaire à sauvegarder
	 * @param comId id du commentaire édité
	 */
	@Override
	public void saveEditCommentaire(Commentaire commentaire, Long comId) {
		
		Commentaire comEdit = commentaireRepository.findById(comId).get();
		comEdit.setContenu(commentaire.getContenu());		
		commentaireRepository.save(comEdit);
	}

	/**
	 * Pour supprimer un commentaire par son id 
	 * @param comId id du commentaire à supprimer
	 */
	@Override
	public void deleteById(Long comId) {
		commentaireRepository.deleteById(comId);
		
	}

	/**
	 * Pour trouver un commentaire par son id
	 * @param comId id du commentaire
	 * @return le commentaire
	 */
	@Override
	public Optional<Commentaire> findById(Long comId) {
		Optional<Commentaire> commentaire = commentaireRepository.findById(comId);
		return commentaire;
	}

	/**
	 * Pour lister tous les commentaire liés à un spot
	 * @param spotId id du spot
	 * @return la liste de commentaires
	 */
	@Override
	public List<Commentaire> findCommentaireBySpot(Long spotId) {
		List<Commentaire> commentaires = commentaireRepository.findCommentaireBySpot(spotId);
		return commentaires ;
	}

}
