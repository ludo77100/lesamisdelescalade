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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.UtilisateurForm;
import com.ludo.metier.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	@Autowired
	private UtilisateurService utilisateurService ;
	

	@GetMapping("/inscriptionUtilisateur")
	public String formUtilisateur(Model model) {
		
		model.addAttribute("utilisateur", new Utilisateur());
		
		return "formEnregistrementUtilisateur" ;
	}
	
	@PostMapping("/confInscription")
	public String saveUtilisateur(
			Model model, 
			@ModelAttribute("formEnregistrementUtilisateur")UtilisateurForm utilisateurForm, 
			@Valid Utilisateur utilisateur, 
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		if (utilisateurRepository.findByPseudo(utilisateurForm.getPseudo()) == null && utilisateurRepository.findByEmail(utilisateurForm.getEmail()) == null) {
			utilisateurService.saveUtilisateur(utilisateurForm, result);
			return "redirect:/";
		} else {
			if (utilisateurRepository.findByPseudo(utilisateurForm.getPseudo()) != null) {
				result.rejectValue("pseudo", "utilisateur.pseudo", "Ce pseudo est déjà utilisé, merci d'en choisir un autre");
			}
			if (utilisateurRepository.findByEmail(utilisateurForm.getEmail()) != null) {
				result.rejectValue("email", "utilisateur.email", "Un compte existe déjà avec cette adresse email");
			}
			model.addAttribute("utilisateur", utilisateur);
			return "formEnregistrementUtilisateur" ;
		}
	}
	
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
}
