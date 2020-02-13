package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.service.SpotService;

/**
 * Implémentation Service spot pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class SpotServiceImpl implements SpotService{

	@Autowired
	SpotRepository spotRepository ;
	@Autowired
	UtilisateurRepository utilisateurRepository ;
	
	/**
	 * Pour trouver un spot en fonction de son id
	 * @param spotId id du spot 
	 * @return un spot
	 */
	@Override
	public Optional<Spot> findById(Long spotId) {
		Optional<Spot> spot = spotRepository.findById(spotId) ;
		return spot;
	}

	/**
	 * Pour sauvegarder un nouveau spot
	 * @param spot instance du spot à sauvegarder
	 */
	@Override
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

	/**
	 * Pour sauvegarder un spot édité
	 * @param spot instance du spot édité à sauvergarder
	 * @param spotId id du spot à édité
	 */
	@Override
	public void saveEditSpot(Spot spot, Long spotId) {
		
		Spot spotEdit = spotRepository.findById(spotId).get();
		
		spotEdit.setNom(spot.getNom());
		spotEdit.setLocalite(spot.getLocalite());
				
		spotRepository.save(spotEdit);
		
	}

	/**
	 * Pour rendre un spot officiel, et pour le repasser en non officiel
	 * @param spotId id du spot 
	 */
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

	/**
	 * Pour supprimer une spot par son id
	 * @param spotId id du spot à supprimer
	 */
	@Override
	public void deleteById(Long spotId) {
		spotRepository.deleteById(spotId);
		
	}

	/**
	 * Pour lister tous les spots 
	 * @return la liste des spots
	 */
	@Override
	public List<Spot> findAll() {
		List<Spot> spots = spotRepository.findAll();
		return spots ;
	}

}
