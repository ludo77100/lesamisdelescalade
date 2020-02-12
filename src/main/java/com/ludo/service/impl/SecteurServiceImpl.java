package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.service.SecteurService;

/**
 * Implémentation Service secteur pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class SecteurServiceImpl implements SecteurService {

	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	/**
	 * Pour lister les secteur lié à un spot
	 * @param spotId id du spot
	 * @return une liste de secteur
	 */
	@Override
	public List<Secteur> findBySpot(Long spotId) {
		List<Secteur> secteurs = secteurRepository.findBySpot(spotId);
		return secteurs ;
	}

	/**
	 * Pour trouver un secteur par son id
	 * @param secteurId id du secteur
	 * @return un secteur
	 */
	@Override
	public Optional<Secteur> findById(Long secteurId) {
		Optional<Secteur> secteur = secteurRepository.findById(secteurId);
		return secteur;
	}

	/**
	 * Pour sauvegarder un nouveau secteur
	 * @param spotId id du spot auquel le secteur est lié
	 * @param secteur instance du secteur à sauvegarder
	 */
	@Override
	public void saveSecteur(Long spotId, Secteur secteur) {
		Secteur newSecteur = new Secteur();
		
		newSecteur.setNomSecteur(secteur.getNomSecteur());
		newSecteur.setLocalisation(secteur.getLocalisation());
		newSecteur.setTypeRoche(secteur.getTypeRoche());
		
		Spot siteSec = spotRepository.findById(spotId).get();
		
		newSecteur.setSpot(siteSec);
		secteurRepository.save(newSecteur);
	}

	/**
	 * Pour saugegarder un secteur édité
	 * @param secteur instance du secteur édité
	 * @param secteurId id du secteur à édité
	 */
	@Override
	public void saveEditSecteur(Secteur secteur, Long secteurId) {

		Secteur secteurEdit = secteurRepository.findById(secteurId).get();
		
		secteurEdit.setNomSecteur(secteur.getNomSecteur());
		secteurEdit.setLocalisation(secteur.getLocalisation());
		secteurEdit.setTypeRoche(secteur.getTypeRoche());
		
		secteurRepository.save(secteurEdit);
	}

	/**
	 * Pour supprimer un secteur 
	 * @param secteurId id du secteur à supprimer
	 */
	@Override
	public void deleteById(Long secteurId) {
		secteurRepository.deleteById(secteurId);		
	}

	
	
}
