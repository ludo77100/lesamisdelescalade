package com.ludo.metier;

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
	public void saveLongueur(Long voieId, LongueurForms longueurForms, BindingResult result) {
		
		Longueur newLongueur = new Longueur();
	
		newLongueur.setCotation(longueurForms.getCotation());
		newLongueur.setLongueurLong(longueurForms.getLongueurLong());
		newLongueur.setNombrePoints(longueurForms.getNombrePoints());
		
		Voie voie = voieRepository.findById(voieId).get();
		
		newLongueur.setVoie(voie);
		
		longueurRepository.save(newLongueur);
	}
	
	/*
	 * Méthode pour l'édition d'une longueur
	 */
	public void saveEditLongueur(LongueurForms longueurForms, Long longueurId) {
		
		Longueur longueurEdit = longueurRepository.findById(longueurId).get();
		
		longueurEdit.setCotation(longueurForms.getCotation());
		longueurEdit.setLongueurLong(longueurForms.getLongueurLong());
		longueurEdit.setNombrePoints(longueurForms.getNombrePoints());
		
		longueurRepository.save(longueurEdit);
	}

}
