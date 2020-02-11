package com.ludo.service.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.VoieRepository;
import com.ludo.entities.Voie;
import com.ludo.service.VoieService;

@Service
@Transactional
public class VoieServiceImpl implements VoieService {

	@Autowired
	private VoieRepository voieRepository ;
	
	@Override
	public List<Voie> findBySecteur(Long secteurId) {
		List<Voie> voies = voieRepository.findBySecteur(secteurId);
		return voies;
	}

}
