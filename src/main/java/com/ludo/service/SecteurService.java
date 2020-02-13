package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Secteur;

public interface SecteurService {

	/**
	 * Pour lister les secteur lié à un spot
	 * @param spotId id du spot
	 * @return une liste de secteur
	 */
	List<Secteur> findBySpot(Long spotId);

	/**
	 * Pour trouver un secteur par son id
	 * @param secteurId id du secteur
	 * @return un secteur
	 */
	Optional<Secteur> findById(Long secteurId);

	/**
	 * Pour sauvegarder un nouveau secteur
	 * @param spotId id du spot auquel le secteur est lié
	 * @param secteur instance du secteur à sauvegarder
	 */
	void saveSecteur(Long spotId, @Valid Secteur secteur);

	/**
	 * Pour saugegarder un secteur édité
	 * @param secteur instance du secteur édité
	 * @param secteurId id du secteur à édité
	 */
	void saveEditSecteur(@Valid Secteur secteur, Long secteurId);

	/**
	 * Pour supprimer un secteur 
	 * @param secteurId id du secteur à supprimer
	 */
	void deleteById(Long secteurId);

}
