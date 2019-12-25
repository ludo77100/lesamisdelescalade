package com.ludo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.ludo.entities.Utilisateur;
import com.ludo.entities.Voie;
import com.ludo.forms.VoieForm;
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
	private VoieService voieService;
	@Autowired
	private LongueurRepository longueurRepository;
	
	/////////////////////////DISPLAY VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Controller pour afficher les information détaillées de la voie slectioné sur la vue précédente
	 * Affiche également la liste des longueurs associés à cette voie
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}")
	public String afficherVoie(Model model, 
			@PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId, 
			@PathVariable("voieId") Long voieId) {

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

	/////////////////////////AJOUT VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Controller pour accéder au formulaire d'ajout de voie lié à un secteur
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie")
	public String formVoie(
			Model model, 
			@PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId) {
		
		Spot spot = spotRepository.findById(spotId).get();
		Secteur secteur = secteurRepository.findById(secteurId).get();
		
		return "formVoie";
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder pour un nouveau secteur
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie/save")
	public String saveVoie(
			Model model, 
			@ModelAttribute("voieForm") VoieForm voieForm,
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId") Long secteurId, 
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		voieService.saveVoie(secteurId, voieForm, result);

		return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
	}
	
	/////////////////////////EDITION VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Controller pour accéder à l'edition d'une voie
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/editVoie/{voieId}")
	public String editVoie(
			Model model, 
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId){
		
		Optional<Voie> v = voieRepository.findById(voieId);
		Voie voie = null ;
		
		if(v.isPresent()) {
			voie = v.get();
			}
		model.addAttribute("voie", voie);
		
		return "editVoie" ;
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder dans le formulaire d'étion d'une voie
	 * Il renvoie vers la voie qui vient d'être édité
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/saveEditVoie/{voieId}")
	public String saveEditVoie(
			Model model, 
			@ModelAttribute("voieForm")VoieForm voieForm,
			@PathVariable("spotId")Long spotId,
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId) {
		
		voieService.saveEditVoie(voieForm, voieId);
		
		return "redirect:/spot/"+ spotId + "/secteur/" +secteurId+ "/voie/" + voieId;
	}
	
	/////////////////////////SUPPRESSION VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Cette méthode permet la suppression d'une voie. Elle execute une
	 * vérification de rôle. Seul le rôle ADMINISTRATOR peut supprimer une voie
	 * Le lien ne s'affiche que pour les ADMIN côté front, mais permet de protéger
	 * contre un anonyme qui taperait le PATH à la main dans son naviguateur
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/deleteVoie/{voieId}")
	public String deleteVoie(
			@PathVariable("voieId") Long voieId, 
			@PathVariable("secteurId") Long secteurId,
			@PathVariable("spotId") Long spotId, 
			final RedirectAttributes redirect,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				voieRepository.deleteById(voieId);
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
			} else
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
		}
	}
}
