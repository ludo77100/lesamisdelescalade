package com.ludo.service;

import java.util.List;
import java.util.Optional;

import com.ludo.entities.Commentaire;

/**
 * Couche Service commentaire pour l'application
 * @author A87671
 *
 */
public interface CommentaireService {

	/**
	 * Pour sauvegarder un nouveau commentaire
	 * @param commentaire commentaire à sauvegarder
	 * @param spotId id du spot auquel est lié le commentaire
	 */
	public void saveCommentaire(Commentaire commentaire, Long spotId);

	/**
	 * Pour sauvegarder un commentaire édité
	 * @param commentaire commentaire à sauvegarder
	 * @param comId id du commentaire édité
	 */
	public void saveEditCommentaire(Commentaire commentaire, Long comId);

	/**
	 * Pour supprimer un commentaire par son id 
	 * @param comId id du commentaire à supprimer
	 */
	public void deleteById(Long comId);

	/**
	 * Pour trouver un commentaire par son id
	 * @param comId id du commentaire
	 * @return le commentaire
	 */
	public Optional<Commentaire> findById(Long comId);

	/**
	 * Pour lister tous les commentaire liés à un spot
	 * @param spotId id du spot
	 * @return la liste de commentaires
	 */
	public List<Commentaire> findCommentaireBySpot(Long spotId);



}
