package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Spot;

public interface SpotService {

	/**
	 * Pour trouver un spot en fonction de son id
	 * @param spotId id du spot 
	 * @return un spot
	 */
	Optional<Spot> findById(Long spotId);

	/**
	 * Pour sauvegarder un nouveau spot
	 * @param spot instance du spot à sauvegarder
	 */
	void saveSpot(@Valid Spot spot);

	/**
	 * Pour sauvegarder un spot édité
	 * @param spot instance du spot édité à sauvergarder
	 * @param spotId id du spot à édité
	 */
	void saveEditSpot(@Valid Spot spot, Long spotId);

	/**
	 * Pour rendre un spot officiel, et pour le repasser en non officiel
	 * @param spotId id du spot 
	 */
	void rendreOfficiel(Long spotId);

	/**
	 * Pour supprimer une spot par son id
	 * @param spotId id du spot à supprimer
	 */
	void deleteById(Long spotId);

	/**
	 * Pour lister tous les spots 
	 * @return la liste des spots
	 */
	List<Spot> findAll();

}
