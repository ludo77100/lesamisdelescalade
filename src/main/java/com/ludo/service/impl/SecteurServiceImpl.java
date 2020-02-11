package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.service.SecteurService;

@Service
@Transactional
public class SecteurServiceImpl implements SecteurService {

	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	@Override
	public List<Secteur> findBySpot(Long spotId) {
		List<Secteur> secteurs = secteurRepository.findBySpot(spotId);
		return secteurs ;
	}

	@Override
	public Optional<Secteur> findById(Long secteurId) {
		Optional<Secteur> secteur = secteurRepository.findById(secteurId);
		return secteur;
	}

	@Override
	public void saveSecteur(Long spotId, @Valid Secteur secteur) {
		Secteur newSecteur = new Secteur();
		
		newSecteur.setNomSecteur(secteur.getNomSecteur());
		newSecteur.setLocalisation(secteur.getLocalisation());
		newSecteur.setTypeRoche(secteur.getTypeRoche());
		
		Spot siteSec = spotRepository.findById(spotId).get();
		
		newSecteur.setSpot(siteSec);
		secteurRepository.save(newSecteur);
	}

	@Override
	public void saveEditSecteur(@Valid Secteur secteur, Long secteurId) {

		Secteur secteurEdit = secteurRepository.findById(secteurId).get();
		
		secteurEdit.setNomSecteur(secteur.getNomSecteur());
		secteurEdit.setLocalisation(secteur.getLocalisation());
		secteurEdit.setTypeRoche(secteur.getTypeRoche());
		
		secteurRepository.save(secteurEdit);
	}

	@Override
	public void deleteById(Long secteurId) {
		secteurRepository.deleteById(secteurId);		
	}

	
	
}
