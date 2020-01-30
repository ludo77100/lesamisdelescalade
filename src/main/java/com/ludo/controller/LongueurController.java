package com.ludo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.ludo.forms.LongueurForms;
import com.ludo.metier.LongueurService;


@Controller
public class LongueurController {
	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	@Autowired
	private VoieRepository voieRepository;
	@Autowired
	private LongueurRepository longueurRepository;
	@Autowired
	private LongueurService longueurService;
	
	/////////////////////////AJOUT LONGUEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Controller pour accéder au formulaire d'ajout de longueur lié à une voie
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}/ajouterLongueur")
	public String formLongueur(
			Model model, 
			@PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId, 
			@PathVariable("voieId") Long voieId,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		Spot spot = spotRepository.findById(spotId).get();
		Secteur secteur = secteurRepository.findById(secteurId).get();
		Voie voie = voieRepository.findById(voieId).get();
		
		model.addAttribute("longueur", new Longueur());
		
		return "formLongueur";
		}
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder pour une nouvelle longueur
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}/ajouterLongueur/save")
	public String saveLongueur(
			Model model, 
			@ModelAttribute("longueurForm") LongueurForms longueurForms,
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId") Long secteurId,
			@PathVariable("voieId") Long voieId, 
			@Valid Longueur longueur,
			BindingResult result, 
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "formLongueur";			
		} else {
		
		longueurService.saveLongueur(voieId, longueurForms, result);

		return "redirect:/spot/" + spotId + "/secteur/" + secteurId + "/voie/" + voieId;
		}
	}
	
	/////////////////////////EDITION LONGUEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\	
	
	/*
	 * Controller pour accéder à l'edition d'une longueur
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}/editLongueur/{longueurId}")
	public String editLongueur(
			Model model, 
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId,
			@PathVariable("longueurId")Long longueurId,
			HttpServletRequest request){
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		Optional<Longueur> l = longueurRepository.findById(longueurId);
		Longueur longueur = null ;
		
		if(l.isPresent()) {
			longueur = l.get();
			}
		model.addAttribute("longueur", longueur);
		
		return "editlongueur" ;
		}
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder dans le formulaire d'étion d'une longueur
	 * Il renvoie vers la liste des longueurs
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}/saveEditLongueur/{longueurId}")
	public String saveEditLongueur(
			Model model, 
			@ModelAttribute("longueurForm")LongueurForms longueurForm,
			@PathVariable("spotId")Long spotId,
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId,
			@PathVariable("longueurId")Long longueurId,
			@Valid Longueur longueur,
			BindingResult result) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("longueurId", longueurId);
			return "editLongueur" ;
		}
		
		longueurService.saveEditLongueur(longueurForm, longueurId);
		
		return "redirect:/spot/"+ spotId + "/secteur/" +secteurId+ "/voie/" + voieId ;
	}
	
	/////////////////////////SUPPRESSION LONGUEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Cette méthode permet la suppression d'une longueur. Elle execute une
	 * vérification de rôle. Seul le rôle ADMINISTRATOR peut supprimer une longueur
	 * Le lien ne s'affiche que pour les ADMIN côté front, mais permet de protéger
	 * contre un anonyme qui taperait le PATH à la main dans son naviguateur
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}/deleteLongueur/{longueurId}")
	public String deleteLongueur(
			HttpServletRequest request, 
			@PathVariable("longueurId") long longueurId,
			@PathVariable("voieId") Long voieId,
			@PathVariable("secteurId") Long secteurId,
			@PathVariable("spotId") Long spotId, 
			final RedirectAttributes redirect) {

		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				longueurRepository.deleteById(longueurId);
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId + "/voie/" + voieId;
			} else {
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId + "/voie/" + voieId;
			}
		}
	}
}
