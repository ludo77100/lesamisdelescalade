package com.ludo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;

@Service
public class SecteurServiceOld {
	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	/*
	 * Méthode pour l'ajout d'un nouveau secteur
	 */
	public void saveSecteur(Long idSite, Secteur secteur) {
		
		Secteur newSecteur = new Secteur();
		
		newSecteur.setNomSecteur(secteur.getNomSecteur());
		newSecteur.setLocalisation(secteur.getLocalisation());
		newSecteur.setTypeRoche(secteur.getTypeRoche());
		
		Spot siteSec = spotRepository.findById(idSite).get();
		
		newSecteur.setSpot(siteSec);
		secteurRepository.save(newSecteur);
	
	}

	/*
	 * Méthode pour l'édition d'un secteur
	 */
	public void saveEditSecteur(Secteur secteur, Long secteurId) {

		Secteur secteurEdit = secteurRepository.findById(secteurId).get();
		
		secteurEdit.setNomSecteur(secteur.getNomSecteur());
		secteurEdit.setLocalisation(secteur.getLocalisation());
		secteurEdit.setTypeRoche(secteur.getTypeRoche());
		
		secteurRepository.save(secteurEdit);
	}

}
