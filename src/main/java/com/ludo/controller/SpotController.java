package com.ludo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.SpotForm;
import com.ludo.metier.SpotService;

@Controller
public class SpotController {
	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private SecteurRepository secteurRepository ;
	@Autowired
	private SpotService spotService ;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	/*
	 * Controller pour la page d'accueil du site
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/*
	 * Controller qui renvoie la liste des spots
	 */
	@GetMapping("/listeSpot")
	public String listeSpot(
			Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "4")int s,
			@RequestParam(name="mc", defaultValue = "")String mc) {
		
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String pseudoUtilCo = utilDet.getUsername() ;
		
		Page<Spot> pageListeSpot = 
				spotRepository.chercher("%"+mc+"%", PageRequest.of(p, s));
		model.addAttribute("listeSpot", pageListeSpot.getContent());
		int[] pages = new int[pageListeSpot.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("pseudoUtilCo", pseudoUtilCo);
	
		return "listeSpot";
		
	}
	
	/*
	 * Controller qui permet d'afficher les détails d'un spot
	 * affiche une liste des secteurs lié au spot choisi
	 */
	@GetMapping("/spot/{spotId}")
	public String afficherSpot(
			Model model, 
			@PathVariable("spotId") Long spotId) {
		
		Spot spot = spotRepository.findById(spotId).get();
		model.addAttribute("spotInfo", spot);
		
		List <Secteur> listeSecteur = secteurRepository.findBySpot(spotId);
		model.addAttribute("listeSecteur", listeSecteur);
		
		return "spot";
	}
	
	/*
	 * Controller qui renvoie vers le formulaire d'ajout de spot
	 */
	@GetMapping("/ajouter")
	public String formSpot(Model model) {
		
		model.addAttribute("spot", new Spot());
		
		return "formSpot" ;
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder dans formulaire d'ajout de spot.
	 */
	@PostMapping("/save")
	public String saveSpot(
			Model model,
			@ModelAttribute("spotForm") SpotForm spotForm, 
			final RedirectAttributes redirectAttributes) {
		
		spotService.saveSpot(spotForm);
		
		return "redirect:/listeSpot" ;
	}
	
	/*
	 * Cette méthode permet la suppression d'une longueur. Elle execute une
	 * vérification de rôle. Seul le rôle ADMINISTRATOR peut supprimer une longueur
	 * Le lien ne s'affiche que pour les ADMIN côté front, mais permet de protéger
	 * contre un anonyme qui taperait le PATH à la main dans son naviguateur
	 */
	@GetMapping("/deleteSpot/{spotId}")
	public String deleteSpot(
			@PathVariable("spotId") Long spotId, 
			final RedirectAttributes redirect,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Spot spot = spotRepository.findById(spotId).get(); //On récupère le spot en cours 

			if (utilDet.getUsername().equals(spot.getUtilisateur().getUsername()) || utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				spotRepository.deleteById(spotId); //On supprime le spot 
				return "redirect:/listeSpot";
			} else {
				return "redirect:/listeSpot";
			}
		}
	}
	
	/*
	 * Controller pour accéder à l'édition d'un spot
	 */
	@GetMapping("/editSpot/{spotId}")
	public String editSpot(Model model, @PathVariable("spotId") Long spotId){
		Optional<Spot> s = spotRepository.findById(spotId);
		Spot spot = null ;
		
		if (s.isPresent()) {
			spot = s.get();
		}
		model.addAttribute("spot", spot);
		return "editSpot" ;
	}
	
	/*
	 * Controller pour l'action du bouton sauvegarder du formulaire d'édition d'un spot
	 * Il renvoie sur le secteur qui vient d'être édité
	 */
	@PostMapping("/saveEditSpot/{spotId}")
	public String saveEditSpot(Model model,@ModelAttribute("spotForm") SpotForm spotForm, @PathVariable("spotId")Long spotId) {
		
		spotService.saveEditSpot(spotForm, spotId);
		
		return "redirect:/spot/"+ spotId ;
	}
}
