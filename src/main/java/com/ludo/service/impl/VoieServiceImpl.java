package com.ludo.service.impl;



import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Voie;
import com.ludo.service.VoieService;

@Service
@Transactional
public class VoieServiceImpl implements VoieService {

	@Autowired
	private VoieRepository voieRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	@Override
	public List<Voie> findBySecteur(Long secteurId) {
		List<Voie> voies = voieRepository.findBySecteur(secteurId);
		return voies;
	}

	@Override
	public Optional<Voie> findById(Long voieId) {
		Optional<Voie> voie = voieRepository.findById(voieId);
		return voie;
	}

	@Override
	public void saveVoie(Long secteurId, @Valid Voie voie) {
	Voie newVoie = new Voie();
		
		newVoie.setNomVoie(voie.getNomVoie());
		newVoie.setCotation(voie.getCotation());
		newVoie.setLongueurVoie(voie.getLongueurVoie());
		newVoie.setEquipee(voie.getEquipee());
		
		Secteur secteurVoie = secteurRepository.findById(secteurId).get();
		newVoie.setSecteur(secteurVoie);
			
		voieRepository.save(newVoie);
	}

	@Override
	public void saveEditVoie(@Valid Voie voie, Long voieId) {

		Voie voieEdit = voieRepository.findById(voieId).get();
		
		voieEdit.setNomVoie(voie.getNomVoie());
		voieEdit.setCotation(voie.getCotation());
		voieEdit.setLongueurVoie(voie.getLongueurVoie());
		voieEdit.setEquipee(voie.getEquipee());
		
		voieRepository.save(voieEdit);
	}

	@Override
	public void deleteById(Long voieId) {
		
		voieRepository.deleteById(voieId);
		
	}

}
