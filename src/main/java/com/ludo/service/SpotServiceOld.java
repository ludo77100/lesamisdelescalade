package com.ludo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;

@Service
public class SpotServiceOld {
	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;

	/*
	 * Méthode pour l'ajout d'un nouveau spot
	 */
	public void saveSpot(Spot spot) {
		
		Spot newSpot = new Spot();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newSpot.setNom(spot.getNom());
		newSpot.setLocalite(spot.getLocalite());
		newSpot.setUtilisateur(utilisateur);
		newSpot.setOfficiel(false);

		spotRepository.save(newSpot);
	}

	/*
	 * Méthode pour l'édition d'un spot
	 */
	public void saveEditSpot(Spot spot, Long spotId) {
		Spot spotEdit = spotRepository.findById(spotId).get();
		
		spotEdit.setNom(spot.getNom());
		spotEdit.setLocalite(spot.getLocalite());
				
		spotRepository.save(spotEdit);
	}
	
	/*
	 *Change le statut d'un spot officiel les amis de l'escalade 
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
