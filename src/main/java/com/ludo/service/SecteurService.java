package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;

public interface SecteurService {

	List<Secteur> findBySpot(Long spotId);

	Optional<Secteur> findById(Long secteurId);

	void saveSecteur(Long spotId, @Valid Secteur secteur);

	void saveEditSecteur(@Valid Secteur secteur, Long secteurId);

	void deleteById(Long secteurId);

}
