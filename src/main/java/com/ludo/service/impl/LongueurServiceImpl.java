package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.LongueurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Longueur;
import com.ludo.entities.Voie;
import com.ludo.service.LongueurService;

/**
 * Implémentation Service longueur pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class LongueurServiceImpl implements LongueurService {

	@Autowired
	private LongueurRepository longueurRepository ;
	@Autowired
	private VoieRepository voieRepository ;
	
	/**
	 * Pour lister les longueurs lié à une voie
	 * @param voieId id de la voie
	 * @return liste de longueur
	 */
	@Override
	public List<Longueur> findByVoie(Long voieId) {
		List<Longueur> longueurs = longueurRepository.findByVoie(voieId);
		return longueurs;
	}

	/**
	 * Pour sauvegarder une nouvelle longueur
	 * @param voieId id de la voie à laquelle la longueur est lié
	 * @param longueur instance de la longueur à sauvegarder
	 */
	@Override
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

	/**
	 * Pour trouver une longueur par son id
	 * @param longueurId id de la longueur
	 * @return une longueur
	 */
	@Override
	public Optional<Longueur> findById(Long longueurId) {
		Optional<Longueur> longueur = longueurRepository.findById(longueurId);
		return longueur;
	}

	/**
	 * Pour sauvegarder une longueur édité
	 * @param longueur instance de la longueur à sauvegarder
	 * @param longueurId id de la longueur édité à sauvegarder
	 */
	@Override
	public void saveEditLongueur(Longueur longueur, Long longueurId) {
		
		Longueur longueurEdit = longueurRepository.findById(longueurId).get();
		
		longueurEdit.setNomLongueur(longueur.getNomLongueur());
		longueurEdit.setCotation(longueur.getCotation());
		longueurEdit.setLongueurLong(longueur.getLongueurLong());
		longueurEdit.setNombrePoints(longueur.getNombrePoints());
		
		longueurRepository.save(longueurEdit);
		
	}
	
	/**
	 * Pour supprimer une longueur par son id 
	 * @param longueurId id de la longueur
	 */
	@Override
	public void deleteById(long longueurId) {
		longueurRepository.deleteById(longueurId);
	}
}
