package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Voie;

/**
 * Couche Service voie pour l'application
 * @author A87671
 *
 */
public interface VoieService {

	/**
	 * Pour lister les voies appartantn à un secteur
	 * @param secteurId id du secteur
	 * @return une liste de voie
	 */
	List<Voie> findBySecteur(Long secteurId);

	/**
	 * Pour trouver une voie par on id
	 * @param voieId id de la voie 
	 * @return une voie
	 */
	Optional<Voie> findById(Long voieId);

	/**
	 * Pour sauvegarder une nouvelle voie
	 * @param secteurId id du secteur auqel la voie est lié
	 * @param voie instance de la voie à sauvegarder
	 */
	void saveVoie(Long secteurId, @Valid Voie voie);

	/**
	 * Pour sauvegarder une voie édité
	 * @param voie instance de la voie édité à sauvegarder
	 * @param voieId id de la voie à éditer
	 */
	void saveEditVoie(@Valid Voie voie, Long voieId);

	/**
	 * Pour supprimer une voie par son id
	 * @param voieId id de la voie à supprimer
	 */
	void deleteById(Long voieId);

}
