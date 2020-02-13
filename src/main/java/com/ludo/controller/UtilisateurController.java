package com.ludo.controller;

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

import com.ludo.dao.UtilisateurRepository;
import com.ludo.dto.UtilisateurDto;
import com.ludo.entities.Utilisateur;
import com.ludo.service.UtilisateurService;

/**
 * Controller pour la partie utilisateur de l'application
 * @author A87671
 *
 */
@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService ;
	
	/**
	 * Controller pour accéder au formulaire d'inscription
	 * @param model instance du model en cours
	 * @return le formulaire d'inscription
	 */
	@GetMapping("/inscriptionUtilisateur")
	public String formUtilisateur(Model model) {
		
		UtilisateurDto utilisateurDto = new UtilisateurDto();
		model.addAttribute("utilisateur", utilisateurDto);
		
		return "formEnregistrementUtilisateur" ;
	}
	
	/**
	 * Controller pour sauvegarder l'utilisateur
	 * @param model instance du model en cours
	 * @param utilisateurDto instance de l'utilisateur en cours d'inscription
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return vers la liste des spots
	 */
	@PostMapping("/confInscription")
	public String saveUtilisateur(
			Model model, 
			@ModelAttribute("utilisateur") @Valid UtilisateurDto utilisateurDto,
			BindingResult result) {
		
		if (utilisateurService.findByPseudo(utilisateurDto.getPseudo()) == null && utilisateurService.findByEmail(utilisateurDto.getEmail()) == null) {			
			
			utilisateurService.saveUtilisateur(utilisateurDto);
			return "redirect:/";
		} else {
			if (utilisateurService.findByPseudo(utilisateurDto.getPseudo()) != null) {
				result.rejectValue("pseudo", "utilisateur.pseudo", "Ce pseudo est déjà utilisé, merci d'en choisir un autre");
			}
			if (utilisateurService.findByEmail(utilisateurDto.getEmail()) != null) {
				result.rejectValue("email", "utilisateur.email", "Un compte existe déjà avec cette adresse email");
			}
			model.addAttribute("utilisateur", utilisateurDto);
			return "formEnregistrementUtilisateur" ;
		}
	}
	
	/**
	 * Controller pour accéder aux informations personnelles de l'utilisateur
	 * @param model instance du model en cours 
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return la vue des information personnelles de l'utilisateur connecté
	 */
	@GetMapping("/infosPersonnelles")
	public String infoPersoUtil(Model model, HttpServletRequest request) {

		if (request.getRemoteUser() == null) {
			return "formConnexion";			
		} else {
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("infosUtilisateur", utilDet);
			return "/infosPerso";
		}
	}
	
	@Autowired
	UtilisateurRepository utilisateurRepository ;
	
	@GetMapping("supprimerUtilisateur/{userId}")
	public String supprimerUtilisateur(@PathVariable("userId")Long userId) {
		utilisateurRepository.deleteById(userId);
		
		return "redirect:/listeSpot" ;
	}
}
