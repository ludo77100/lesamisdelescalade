package com.ludo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.forms.SecteurForm;

@Service
public class SecteurService {
	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	
	public void saveSecteur(Long idSite, SecteurForm secteurForm, BindingResult result) {
		
		Secteur newSecteur = new Secteur();
		newSecteur.setNomSecteur(secteurForm.getNomSecteur());
		newSecteur.setLocalisation(secteurForm.getLocalisation());
		newSecteur.setAcces(secteurForm.getAcces());
		newSecteur.setTypeRoche(secteurForm.getTypeRoche());
		newSecteur.setNombreVoies(secteurForm.getNombreVoies());
		
		Spot siteSec = spotRepository.findById(idSite).get();
		
		newSecteur.setSpot(siteSec);
		secteurRepository.save(newSecteur);
	
	}

	public void saveEditSecteur(SecteurForm secteurForm, Long secteurId) {

		Secteur secteurEdit = secteurRepository.findById(secteurId).get();
		
		secteurEdit.setNomSecteur(secteurForm.getNomSecteur());
		secteurEdit.setLocalisation(secteurForm.getLocalisation());
		secteurEdit.setAcces(secteurForm.getAcces());
		secteurEdit.setTypeRoche(secteurForm.getTypeRoche());
		secteurEdit.setNombreVoies(secteurForm.getNombreVoies());
		
		secteurRepository.save(secteurEdit);
	}

}
