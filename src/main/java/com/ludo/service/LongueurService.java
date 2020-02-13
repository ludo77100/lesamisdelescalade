package com.ludo.service;

import java.util.List;
import java.util.Optional;

import com.ludo.entities.Longueur;

/**
 * Couche Service longueur pour l'application
 * @author A87671
 *
 */
public interface LongueurService {

	/**
	 * Pour lister les longueurs lié à une voie
	 * @param voieId id de la voie
	 * @return liste de longueur
	 */
	List<Longueur> findByVoie(Long voieId);

	/**
	 * Pour sauvegarder une nouvelle longueur
	 * @param voieId id de la voie à laquelle la longueur est lié
	 * @param longueur instance de la longueur à sauvegarder
	 */
	void saveLongueur(Long voieId, Longueur longueur);

	/**
	 * Pour sauvegarder une longueur édité
	 * @param longueur instance de la longueur à sauvegarder
	 * @param longueurId id de la longueur édité à sauvegarder
	 */
	void saveEditLongueur(Longueur longueur, Long longueurId);
	
	/**
	 * Pour trouver une longueur par son id
	 * @param longueurId id de la longueur
	 * @return une longueur
	 */
	Optional<Longueur> findById(Long longueurId);

	/**
	 * Pour supprimer une longueur par son id 
	 * @param longueurId id de la longueur
	 */
	void deleteById(long longueurId);

}
