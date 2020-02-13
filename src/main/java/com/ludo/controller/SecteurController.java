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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.entities.Voie;
import com.ludo.service.SecteurService;
import com.ludo.service.SpotService;
import com.ludo.service.VoieService;

/**
 * Controller pour la partie secteur de l'application
 * @author A87671
 *
 */

@Controller
public class SecteurController {
	
	@Autowired
	private SecteurService secteurService ;
	@Autowired
	private SpotService spotService ;
	@Autowired
	private VoieService voieService ;
	
	/////////////////////////DISPLAY SECTEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * Controller qui permet d'afficher les détails du spot et du secteur
	 * Affiche une liste de voie liés au secteur choisi
	 * @param model instance du model en cours 
	 * @param spotId id du spot à afficher
	 * @param secteurId id du secteur à afficher
	 * @return une vue des détails du spot et secteur choisi, une liste de voies
	 */
	@GetMapping("/spot/{spotId}/secteur/{secteurId}")
	public String afficherSecteur(
			Model model, 
			@PathVariable("spotId")Long spotId, 
			@PathVariable("secteurId")Long secteurId) {
		
		Spot spot = spotService.findById(spotId).get();
		Secteur secteur = secteurService.findById(secteurId).get();
		
		model.addAttribute("spotInfo", spot);
		model.addAttribute("secteurInfo", secteur);
		
		List <Voie> listeVoie = voieService.findBySecteur(secteurId);
		model.addAttribute("listeVoie", listeVoie);

		return "secteur";
	}
	
	/////////////////////////AJOUT SECTEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour accéder au formulaire d'ajout de secteur lié à un spot 
	 * @param model instance du model en cours 
	 * @param spotId id du spot auquel le secteur est lié
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'ajout d'un nouveau spot
	 */
	@GetMapping("/spot/{spotId}/ajouterSecteur")
	public String formSecteur(
			Model model,
			@PathVariable("spotId") Long spotId, HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
			
		model.addAttribute("secteur", new Secteur());
		
		return "formSecteur";
		}
	}

	/**
	 * Controller pour l'action du bouton sauvegarder pour un nouveau secteur
	 * @param model instance du model en cours 
	 * @param spotId id du spot auquel le secteur est lié 
	 * @param secteur instance du secteur à ajouter
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return la vue des détails du spot en cours et la liste des secteur liés
	 */
	@PostMapping("/spot/{spotId}/ajouterSecteur/save")
	public String saveSecteur(
			Model model,
			@PathVariable("spotId") Long spotId,
			@Valid Secteur secteur,
			BindingResult result
			) {
		
		if (result.hasErrors()) {
			return "formSecteur" ;			
		}
		
		secteurService.saveSecteur(spotId, secteur);
		
		return "redirect:/spot/" + spotId ;
	}
	
	/////////////////////////EDITION SECTEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour accéder à l'edition d'un secteur
	 * @param model instance du model en cours 
	 * @param spotId id du spot auquel le secteur est lié
	 * @param secteurId id du secteur qui va être édité
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'édition du secteur
	 */
	@GetMapping("/spot/{spotId}/editSecteur/{secteurId}")
	public String editSecteur(
			Model model, 
			@PathVariable("spotId") Long spotId, 
			@PathVariable("secteurId")Long secteurId,
			HttpServletRequest request){
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		Optional<Secteur> s = secteurService.findById(secteurId);
		Secteur secteur = null ;
		
		if(s.isPresent()) {
			secteur = s.get();
			}
		model.addAttribute("secteur", secteur);
		
		return "editSecteur" ;
		}
	}

	/**
	 * Controller pour l'action du bouton sauvegarder dans le formulaire d'étion d'un secteur
	 * @param model instance du model en cours 
	 * @param spotId id du spot auquel le secteur est lié
	 * @param secteurId id du secteur qui est édité
	 * @param secteur instance du secteur en cours d'édition
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return une vue des détails spot et secteurs et liste de voie associes au secteur
	 */
	@PostMapping("/spot/{spotId}/saveEditSecteur/{secteurId}")
	public String saveEditSecteur(
			Model model,  
			@PathVariable("spotId")Long spotId,
			@PathVariable("secteurId")Long secteurId,
			@Valid Secteur secteur,
			BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("secteurId", secteurId);
			return "editSecteur";			
		} else {
		
		secteurService.saveEditSecteur(secteur, secteurId);
		
		return "redirect:/spot/"+ spotId +"/secteur/" +secteurId;
	}
	}
	
	/////////////////////////SUPPRESSION SECTEUR\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Cette méthode permet la suppression d'un secteur. Elle execute une
	 * vérification de rôle. Seul le rôle ADMINISTRATOR peut supprimer une longueur
	 * Le lien ne s'affiche que pour les ADMIN côté front, mais permet de protéger
	 * contre un anonyme qui taperait le PATH à la main dans son naviguateur
	 */
	@GetMapping("/spot/{spotId}/deleteSecteur/{secteurId}")
	public String deleteSecteur(
			@PathVariable("secteurId") Long secteurId, 
			@PathVariable("spotId") Long spotId,
			final RedirectAttributes redirect,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion" ;
		} else {
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				secteurService.deleteById(secteurId);
				return "redirect:/spot/" + spotId;
			} else {
				return "redirect:/spot/" + spotId;
			}
		}
	}
}