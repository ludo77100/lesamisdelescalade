package com.ludo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Voie;

@Service
public class VoieServiceOld {

	@Autowired
	private SecteurRepository secteurRepository ;
	@Autowired
	private VoieRepository voieRepository ;
	
	/*
	 * Méthode pour l'ajout d'une nouvelle voie
	 */
	public void saveVoie(Long secteurId, Voie voie, BindingResult result) {
		
		Voie newVoie = new Voie();
		
		newVoie.setNomVoie(voie.getNomVoie());
		newVoie.setCotation(voie.getCotation());
		newVoie.setLongueurVoie(voie.getLongueurVoie());
		newVoie.setEquipee(voie.getEquipee());
		
		Secteur secteurVoie = secteurRepository.findById(secteurId).get();
		newVoie.setSecteur(secteurVoie);
			
		voieRepository.save(newVoie);
	}

	/*
	 * Méthode pour l'édition d'une voie
	 */
	public void saveEditVoie(Voie voie, Long voieId) {
		
		Voie voieEdit = voieRepository.findById(voieId).get();
		
		voieEdit.setNomVoie(voie.getNomVoie());
		voieEdit.setCotation(voie.getCotation());
		voieEdit.setLongueurVoie(voie.getLongueurVoie());
		voieEdit.setEquipee(voie.getEquipee());
		
		voieRepository.save(voieEdit);
	}
}
