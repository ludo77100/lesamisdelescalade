package com.ludo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Voie;
import com.ludo.forms.VoieForm;

@Service
public class VoieService {

	@Autowired
	private SecteurRepository secteurRepository ;
	@Autowired
	private VoieRepository voieRepository ;
	
	/*
	 * Méthode pour l'ajout d'une nouvelle voie
	 */
	public void saveVoie(Long secteurId, VoieForm voieForm, BindingResult result) {
		
		Voie newVoie = new Voie();
		
		newVoie.setNomVoie(voieForm.getNomVoie());
		newVoie.setCotation(voieForm.getCotation());
		newVoie.setLongueurVoie(voieForm.getLongueurVoie());
		newVoie.setEquipee(voieForm.getEquipee());
		
		Secteur secteurVoie = secteurRepository.findById(secteurId).get();
		newVoie.setSecteur(secteurVoie);
			
		voieRepository.save(newVoie);
	}

	/*
	 * Méthode pour l'édition d'une voie
	 */
	public void saveEditVoie(VoieForm voieForm, Long voieId) {
		
		Voie voieEdit = voieRepository.findById(voieId).get();
		
		voieEdit.setNomVoie(voieForm.getNomVoie());
		voieEdit.setCotation(voieForm.getCotation());
		voieEdit.setLongueurVoie(voieForm.getLongueurVoie());
		voieEdit.setEquipee(voieForm.getEquipee());
		
		voieRepository.save(voieEdit);
	}
}
