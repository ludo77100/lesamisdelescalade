package com.ludo.service;

import java.util.List;

import com.ludo.entities.Voie;

public interface VoieService {

	List<Voie> findBySecteur(Long secteurId);

}
