package com.ludo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.SpotForm;

@Service
public class SpotService {
	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;

	/*
	 * Méthode pour l'ajout d'un nouveau spot
	 */
	public void saveSpot(SpotForm spotForm) {
		
		Spot newSpot = new Spot();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newSpot.setNom(spotForm.getNom());
		newSpot.setLocalite(spotForm.getLocalite());
		newSpot.setCotationMin(spotForm.getCotationMin());
		newSpot.setUtilisateur(utilisateur);
		newSpot.setOfficiel(false);

		spotRepository.save(newSpot);
	}

	/*
	 * Méthode pour l'édition d'un spot
	 */
	public void saveEditSpot(SpotForm spotForm, Long spotId) {
		Spot spotEdit = spotRepository.findById(spotId).get();
		
		spotEdit.setNom(spotForm.getNom());
		spotEdit.setLocalite(spotForm.getLocalite());
		spotEdit.setCotationMin(spotForm.getCotationMin());
				
		spotRepository.save(spotEdit);
	}
	
	/*
	 * Rend officiel les amis de l'escalade un spot
	 */
	public void rendreOfficiel(Long spotId) {
		Spot spotRendreOfficiel = spotRepository.findById(spotId).get();

		if (!spotRendreOfficiel.isOfficiel()) {
			spotRendreOfficiel.setOfficiel(true);
		} else {
			spotRendreOfficiel.setOfficiel(false);
		}
		spotRepository.save(spotRendreOfficiel);
	}

}
