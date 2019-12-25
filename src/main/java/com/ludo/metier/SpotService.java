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

	public void saveSpot(SpotForm spotForm) {
		
		Spot newSpot = new Spot();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newSpot.setNom(spotForm.getNom());
		newSpot.setLocalite(spotForm.getLocalite());
		newSpot.setCotationMin(spotForm.getCotationMin());
		newSpot.setUtilisateur(utilisateur);

		spotRepository.save(newSpot);
	}

	public void saveEditSpot(SpotForm spotForm, Long spotId) {
		Spot spotEdit = spotRepository.findById(spotId).get();
		
		spotEdit.setNom(spotForm.getNom());
		spotEdit.setLocalite(spotForm.getLocalite());
		spotEdit.setCotationMin(spotForm.getCotationMin());
				
		spotRepository.save(spotEdit);
	}

}
