package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.ludo.entities.Spot;
import com.ludo.entities.Voie;

public interface VoieService {

	List<Voie> findBySecteur(Long secteurId);

	Optional<Voie> findById(Long voieId);

	void saveVoie(Long secteurId, @Valid Voie voie, BindingResult result);

	void saveEditVoie(@Valid Voie voie, Long voieId);

	void deleteById(Long voieId);

}
