package com.ludo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.dao.SpotRepository;
import com.ludo.entities.Commentaire;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.service.CommentaireService;
import com.ludo.service.SecteurService;
import com.ludo.service.SpotService;

/**
 * Controller pour la partie spot de l'application
 * @author A87671
 *
 */

@Controller
public class SpotController {
	@Autowired
	private SpotRepository spotRepository; //besoin pour la recherche
	@Autowired
	SpotService spotService ;
	@Autowired
	CommentaireService commentaireService ;
	@Autowired
	SecteurService secteurService ;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/listeSpot";
		}
	
	
	/////////////////////////DISPLAY SPOT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * Controller qui renvoie la liste des spots une recherche de spot
	 * @param model instance du model en cours
	 * @param p page en cours
	 * @param s le nombre de résultat par page
	 * @param mc mot clé pour la recherche
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return une vue de la liste des spots
	 */
	@GetMapping("/listeSpot")
	public String listeSpot(
			Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "15")int s,
			@RequestParam(name="mc", defaultValue = "")String mc,
			HttpServletRequest request) {
		
		Page<Spot> pageListeSpot = 
				spotRepository.chercher("%"+mc+"%", (PageRequest.of(p, s)));
		model.addAttribute("listeSpot", pageListeSpot.getContent());
		int[] pages = new int[pageListeSpot.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		
		if (request.getRemoteUser() != null) {
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String pseudoUtilCo = utilDet.getUsername() ;
			model.addAttribute("pseudoUtilCo", pseudoUtilCo);
			return "listeSpot";
		} else {
		return "listeSpot";
		}
	}
	
	/**
	 * Controller pour accéder aux détails d'un spot
	 * @param model instance du model en cours 
	 * @param spotId id du secteur dont on veut les détails 
	 * @return une vue des détails d'un spot et affiche une liste des secteurs lié au spot
	 */
	@GetMapping("/spot/{spotId}")
	public String afficherSpot(
			Model model, 
			@PathVariable("spotId") Long spotId) {
		
		Spot spot = spotService.findById(spotId).get();
		model.addAttribute("spotInfo", spot);
		
		List <Secteur> listeSecteur = secteurService.findBySpot(spotId);
		model.addAttribute("listeSecteur", listeSecteur);
		
		List<Commentaire> listeCommentaire = commentaireService.findCommentaireBySpot(spotId);
		model.addAttribute("listeCommentaire", listeCommentaire);
		
		return "spot";
	}
	
	/////////////////////////AJOUT SPOT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * Controller qui renvoie vers le formulaire d'ajout de spot
	 * @param model instance du model en cours
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'ajout de spot
	 */
	@GetMapping("/ajouter")
	public String formSpot(Model model, HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		model.addAttribute("spot", new Spot());
		
		return "formSpot" ;
		}
	}
	
	/**
	 * Controller pour l'action du bouton sauvegarder dans formulaire d'ajout de spot
	 * @param model instance du model en cours 
	 * @param spot instance du spot à ajouter
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return une vue de la liste du spot 
	 */
	@PostMapping("/save")
	public String saveSpot(
			Model model,
			@Valid Spot spot,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "formSpot" ;			
		}
		
		spotService.saveSpot(spot);
		
		return "redirect:/" ;
	}
	
	/////////////////////////EDITION SPOT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour accéder à l'édition d'un spot
	 * @param model instance du model en cours 
	 * @param spotId id du spot à édité
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le formulaire d'édition du spot
	 */
	@GetMapping("/editSpot/{spotId}")
	public String editSpot(Model model, @PathVariable("spotId") Long spotId, HttpServletRequest request){
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		Optional<Spot> s = spotService.findById(spotId);
		Spot spot = null ;
		
		if (s.isPresent()) {
			spot = s.get();
		}
		
		model.addAttribute("spot", spot);
		
		return "editSpot" ;
		}
	}

	/**
	 * Controller pour l'action du bouton sauvegarder du formulaire d'édition d'un spot
	 * @param model instance du model en cours 
	 * @param spotId id du spote à édité
	 * @param spot intance du spot à édité
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return une vue des détails du spot qui vient d'être édité
	 */
	@PostMapping("/saveEditSpot/{spotId}")
	public String saveEditSpot(Model model,
			@PathVariable("spotId")Long spotId,
			@Valid Spot spot, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("spotId", spotId);
			return "editSpot";			
		} else {
		
		spotService.saveEditSpot(spot, spotId);
		
		return "redirect:/spot/"+ spotId ;
		}
	}
	
	/////////////////////////SUPPRESSION SPOT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour la suppression d'un spot
	 * @param spotId id du spot à supprimé
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return une vue de la liste des spots
	 */
	@GetMapping("/deleteSpot/{spotId}")
	public String deleteSpot(
			@PathVariable("spotId") Long spotId, 
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Spot spot = spotService.findById(spotId).get();

			if (utilDet.getUsername().equals(spot.getUtilisateur().getUsername()) || utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				spotService.deleteById(spotId); //On supprime le spot 
				return "redirect:/listeSpot";
			} else {
				return "redirect:/listeSpot";
			}
		}
	}
	
	/////////////////////////OFFICIEL LES AMIS DE L'ESCALADE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Cette méthode permet de passer un spot en officiel les amis de l'escalade
	 */
	
	/**
	 * Controller permet de passer un spot en officiel les amis de l'escalade
	 * @param spotId id du spot
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return la vue de la liste des spots
	 */
	@GetMapping("/listeSpot/rendreOfficiel/{spotId}")
	public String rendreOfficiel(
			@PathVariable("spotId")Long spotId,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				spotService.rendreOfficiel(spotId);
				return "redirect:/listeSpot";
			} else {
				return "redirect:/listeSpot";
	}
}
	}
}
