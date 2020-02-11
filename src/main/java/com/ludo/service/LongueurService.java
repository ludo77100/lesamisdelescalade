package com.ludo.service;

import java.util.List;

import com.ludo.entities.Longueur;

public interface LongueurService {

	List<Longueur> findByVoie(Long voieId);

}
