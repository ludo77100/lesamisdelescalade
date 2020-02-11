package com.ludo.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.service.SpotService;

@Service
@Transactional
public class SpotServiceImpl implements SpotService{

	@Autowired
	SpotRepository spotRepository ;
	@Autowired
	UtilisateurRepository utilisateurRepository ;
	
	@Override
	public Optional<Spot> findById(Long spotId) {
		Optional<Spot> spot = spotRepository.findById(spotId) ;
		return spot;
	}

	@Override
	public void saveSpot(@Valid Spot spot) {
		Spot newSpot = new Spot();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		newSpot.setNom(spot.getNom());
		newSpot.setLocalite(spot.getLocalite());
		newSpot.setUtilisateur(utilisateur);
		newSpot.setOfficiel(false);

		spotRepository.save(newSpot);
	}

	@Override
	public void saveEditSpot(@Valid Spot spot, Long spotId) {
		
		Spot spotEdit = spotRepository.findById(spotId).get();
		
		spotEdit.setNom(spot.getNom());
		spotEdit.setLocalite(spot.getLocalite());
				
		spotRepository.save(spotEdit);
		
	}

	@Override
	public void rendreOfficiel(Long spotId) {
		Spot spotRendreOfficiel = spotRepository.findById(spotId).get();

		if (!spotRendreOfficiel.isOfficiel()) {
			spotRendreOfficiel.setOfficiel(true);
		} else {
			spotRendreOfficiel.setOfficiel(false);
		}
		spotRepository.save(spotRendreOfficiel);
	}

	@Override
	public void deleteById(Long spotId) {
		spotRepository.deleteById(spotId);
		
	}

}
