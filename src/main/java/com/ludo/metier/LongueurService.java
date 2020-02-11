package com.ludo.metier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.LongueurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Longueur;
import com.ludo.entities.Voie;
import com.ludo.forms.LongueurForms;

@Service
public class LongueurService {

	
	@Autowired
	VoieRepository voieRepository ;
	@Autowired
	LongueurRepository longueurRepository;
	
	/*
	 * Méthode pour l'ajout d'une nouvelle longueur
	 */
	public void saveLongueur(Long voieId, Longueur longueur) {
		
		Longueur newLongueur = new Longueur();
	
		newLongueur.setNomLongueur(longueur.getNomLongueur());
		newLongueur.setCotation(longueur.getCotation());
		newLongueur.setLongueurLong(longueur.getLongueurLong());
		newLongueur.setNombrePoints(longueur.getNombrePoints());
		
		Voie voie = voieRepository.findById(voieId).get();
		
		newLongueur.setVoie(voie);
		
		longueurRepository.save(newLongueur);
	}
	
	/*
	 * Méthode pour l'édition d'une longueur
	 */
	public void saveEditLongueur(Longueur longueur, Long longueurId) {
		
		Longueur longueurEdit = longueurRepository.findById(longueurId).get();
		
		longueurEdit.setNomLongueur(longueur.getNomLongueur());
		longueurEdit.setCotation(longueur.getCotation());
		longueurEdit.setLongueurLong(longueur.getLongueurLong());
		longueurEdit.setNombrePoints(longueur.getNombrePoints());
		
		longueurRepository.save(longueurEdit);
	}

}
