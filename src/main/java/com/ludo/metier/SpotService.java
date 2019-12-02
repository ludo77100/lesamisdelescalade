package com.ludo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.entities.Spot;
import com.ludo.forms.SpotForm;

@Service
public class SpotService {
	@Autowired
	private SpotRepository spotRepository;

	public void saveSpot(SpotForm spotForm) {
		
		Spot newSpot = new Spot();

		newSpot.setNom(spotForm.getNom());
		newSpot.setLocalite(spotForm.getLocalite());
		newSpot.setCotationMin(spotForm.getCotationMin());

		spotRepository.save(newSpot);
	}

}
