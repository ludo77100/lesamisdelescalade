package com.ludo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.forms.SecteurForm;
import com.ludo.metier.SecteurService;
import com.ludo.metier.SpotService;

@Controller
public class SecteurController {
	
	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private SecteurRepository secteurRepository ;
	@Autowired
	private SecteurService secteurService ;
	@Autowired
	private SpotService spotService ;
	
	
	@GetMapping("/spot/{spotId}/ajouterSecteur")
	public String formSecteur(Model model, @PathVariable("spotId") Long spotId) {
		Spot spot = spotRepository.findById(spotId).get();
		
		return "formSecteur";
	}
	@PostMapping("/spot/{spotId}/ajouterSecteur/save")
	public String saveSecteur(Model model, @ModelAttribute("secteurForm") SecteurForm secteurForm, @PathVariable("spotId") Long spotId, BindingResult result, RedirectAttributes redirectAttributes) {
		
		secteurService.saveSecteur(spotId, secteurForm, result);
		
		return "redirect:/spot/" + spotId ;
	}
}
