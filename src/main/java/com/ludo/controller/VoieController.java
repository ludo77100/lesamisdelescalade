package com.ludo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.LongueurRepository;
import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.dao.VoieRepository;
import com.ludo.entities.Longueur;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.entities.Voie;
import com.ludo.forms.VoieForm;
import com.ludo.metier.LongueurService;
import com.ludo.metier.SecteurService;
import com.ludo.metier.SpotService;
import com.ludo.metier.VoieService;

@Controller
public class VoieController {

	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	@Autowired
	private VoieRepository voieRepository;
	@Autowired
	private LongueurRepository longueurRepository;
	@Autowired
	private SecteurService secteurService;
	@Autowired
	private SpotService spotService;
	@Autowired
	private VoieService voieService;
	@Autowired
	private LongueurService longueurService;

	@GetMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie")
	public String formVoie(Model model, @PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId) {
		Spot spot = spotRepository.findById(spotId).get();
		Secteur secteur = secteurRepository.findById(secteurId).get();
		return "formVoie";
	}

	@PostMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie/save")
	public String saveVoie(Model model, @ModelAttribute("voieForm") VoieForm voieForm,
			@PathVariable("spotId") Long spotId, @PathVariable("secteurId") Long secteurId, BindingResult result,
			RedirectAttributes redirectAttributes) {

		voieService.saveVoie(secteurId, voieForm, result);

		return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
	}

	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}")
	public String afficherVoie(Model model, @PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId, @PathVariable("voieId") Long voieId) {

		Spot spot = spotRepository.findById(spotId).get();
		Secteur secteur = secteurRepository.findById(secteurId).get();
		Voie voie = voieRepository.findById(voieId).get();

		model.addAttribute("spotInfo", spot);
		model.addAttribute("secteurInfo", secteur);
		model.addAttribute("voieInfo", voie);

		List<Longueur> listeLongueur = longueurRepository.findByVoie(voieId);
		model.addAttribute("listeLongueur", listeLongueur);
		return "voie";
	}
}