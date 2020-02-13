package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Voie;
import com.ludo.service.VoieService;

/**
 * Implémentation Service voie pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class VoieServiceImpl implements VoieService {

	@Autowired
	private VoieRepository voieRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	/**
	 * Pour lister les voies appartantn à un secteur
	 * @param secteurId id du secteur
	 * @return une liste de voie
	 */
	@Override
	public List<Voie> findBySecteur(Long secteurId) {
		List<Voie> voies = voieRepository.findBySecteur(secteurId);
		return voies;
	}

	/**
	 * Pour trouver une voie par on id
	 * @param voieId id de la voie 
	 * @return une voie
	 */
	@Override
	public Optional<Voie> findById(Long voieId) {
		Optional<Voie> voie = voieRepository.findById(voieId);
		return voie;
	}

	/**
	 * Pour sauvegarder une nouvelle voie
	 * @param secteurId id du secteur auqel la voie est lié
	 * @param voie instance de la voie à sauvegarder
	 */
	@Override
	public void saveVoie(Long secteurId, Voie voie) {
	Voie newVoie = new Voie();
		
		newVoie.setNomVoie(voie.getNomVoie());
		newVoie.setCotation(voie.getCotation());
		newVoie.setLongueurVoie(voie.getLongueurVoie());
		newVoie.setEquipee(voie.getEquipee());
		
		Secteur secteurVoie = secteurRepository.findById(secteurId).get();
		newVoie.setSecteur(secteurVoie);
			
		voieRepository.save(newVoie);
	}

	/**
	 * Pour sauvegarder une voie édité
	 * @param voie instance de la voie édité à sauvegarder
	 * @param voieId id de la voie à éditer
	 */
	@Override
	public void saveEditVoie(Voie voie, Long voieId) {

		Voie voieEdit = voieRepository.findById(voieId).get();
		
		voieEdit.setNomVoie(voie.getNomVoie());
		voieEdit.setCotation(voie.getCotation());
		voieEdit.setLongueurVoie(voie.getLongueurVoie());
		voieEdit.setEquipee(voie.getEquipee());
		
		voieRepository.save(voieEdit);
	}

	/**
	 * Pour supprimer une voie par son id
	 * @param voieId id de la voie à supprimer
	 */
	@Override
	public void deleteById(Long voieId) {
		
		voieRepository.deleteById(voieId);
	}
}
