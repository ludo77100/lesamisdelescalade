package com.ludo.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ludo.entities.Longueur;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.entities.Voie;
import com.ludo.service.LongueurService;
import com.ludo.service.SecteurService;
import com.ludo.service.SpotService;
import com.ludo.service.VoieService;

/**
 * Controller pour la partie voie de l'application
 * @author A87671
 *
 */
@Controller
public class VoieController {
	
	@Autowired
	private VoieService voieService;
	@Autowired
	private SpotService spotService ;
	@Autowired
	private SecteurService secteurService;
	@Autowired
	private LongueurService longueurService;
	
	/////////////////////////DISPLAY VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour afficher les information détaillées du spot, secteur et de la voie et la liste de longueur associé à la voie
	 * @param model instance du model en cours
	 * @param spotId id du spot 
	 * @param secteurId id du secteur
	 * @param voieId id de la voie
	 * @return une vue détaillé du spot, secteur et voie et liste de longueurs
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/voie/{voieId}")
	public String afficherVoie(Model model, 
			@PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId, 
			@PathVariable("voieId") Long voieId) {

		Spot spot = spotService.findById(spotId).get();
		Secteur secteur = secteurService.findById(secteurId).get();
		Voie voie = voieService.findById(voieId).get();

		model.addAttribute("spotInfo", spot);
		model.addAttribute("secteurInfo", secteur);
		model.addAttribute("voieInfo", voie);

		List<Longueur> listeLongueur = longueurService.findByVoie(voieId);
		model.addAttribute("listeLongueur", listeLongueur);
		
		return "voie";
	}

	/////////////////////////AJOUT VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * Controller pour accéder au formulaire d'ajout de voie lié à un secteur
	 * @param model instance du model en cours
	 * @param spotId id du spot en cours
	 * @param secteurId id du secteur en cours auquel la voie va être ajoutée
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'ajout d'une voie
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie")
	public String formVoie(
			Model model, 
			@PathVariable("spotId") Long spotId,
			@PathVariable("secteurId") Long secteurId,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

		model.addAttribute("voie", new Voie());
		
		return "formVoie";
		}
	}

	/**
	 * Controller pour l'action du bouton sauvegarder pour un nouveau secteur
	 * @param model instance du model en cours 
	 * @param spotId id du spot
	 * @param secteurId id du secteur en cours auquel la voie va être ajoutée
	 * @param voie instance de la voie en cours d'ajout
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return la vue de détails spot, secteur et liste de voies
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/ajouterVoie/save")
	public String saveVoie(
			Model model, 
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId") Long secteurId,
			@Valid Voie voie,
			BindingResult result) {

		if (result.hasErrors()) {
			return "formVoie";
		}
		
		voieService.saveVoie(secteurId, voie);
		
		return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
	}
	
	/////////////////////////EDITION VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour accéder à l'edition d'une voie
	 * @param model instance du model en cours 
	 * @param spotId id du spote
	 * @param secteurId id du secteur
	 * @param voieId id de la voie qui doit être éditée
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'édition de la voie
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/editVoie/{voieId}")
	public String editVoie(
			Model model, 
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId,
			HttpServletRequest request){
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		Optional<Voie> v = voieService.findById(voieId);
		Voie voie = null ;
		
		if(v.isPresent()) {
			voie = v.get();
			}
		model.addAttribute("voie", voie);
		
		return "editVoie" ;
		}
	}

	/**
	 * Controller pour l'action du bouton sauvegarder dans le formulaire d'étion d'une voie
	 * @param model instance du model en cours 
	 * @param spotId id du spot
	 * @param secteurId id du secteur
	 * @param voieId id de la voie en cours d'édition
	 * @param voie instance de la voie en cours d'édition
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return la vue de détails spot, secteur et voie
	 */
	@PostMapping("/spot/{spotId}/secteur/{secteurId}/saveEditVoie/{voieId}")
	public String saveEditVoie(
			Model model, 
			@PathVariable("spotId")Long spotId,
			@PathVariable("secteurId")Long secteurId,
			@PathVariable("voieId")Long voieId,
			@Valid Voie voie,
			BindingResult result) {
		
		if (result.hasErrors()) {
			
			model.addAttribute("voieId", voieId);
			
			return "editVoie";
			
		} else {
		
		voieService.saveEditVoie(voie, voieId);
		
		return "redirect:/spot/"+ spotId + "/secteur/" +secteurId+ "/voie/" + voieId;
		}
	}

	/////////////////////////SUPPRESSION VOIE\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour la suppression d'une voie
	 * @param voieId id de la voie à supprimer 
	 * @param secteurId id du secteur
	 * @param spotId id du spot
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return la vue de détails spot, secteur et liste de voies
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}/deleteVoie/{voieId}")
	public String deleteVoie(
			@PathVariable("voieId") Long voieId, 
			@PathVariable("secteurId") Long secteurId,
			@PathVariable("spotId") Long spotId, 
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				voieService.deleteById(voieId);
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
			} else {
				return "redirect:/spot/" + spotId + "/secteur/" + secteurId;
			}
		}
	}
}
