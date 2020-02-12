package com.ludo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Longueur;

public interface LongueurService {

	List<Longueur> findByVoie(Long voieId);

	void saveLongueur(Long voieId, @Valid Longueur longueur);

	Optional<Longueur> findById(Long longueurId);

	void saveEditLongueur(@Valid Longueur longueur, Long longueurId);

	void deleteById(long longueurId);

}
