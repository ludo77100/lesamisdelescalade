package com.ludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.forms.UtilisateurForm;
import com.ludo.metier.UtilisateurService;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	@Autowired
	private UtilisateurService utilisateurService ;
	

	@GetMapping("/inscriptionUtilisateur")
	public String formUtilisateur() {
		return "formEnregistrementUtilisateur" ;
	}
	
	@PostMapping("/confInscription")
	public String saveUtilisateur(Model model, @ModelAttribute("formEnregistrementUtilisateur")UtilisateurForm utilisateurForm, BindingResult result, RedirectAttributes redirectAttributes) {
		utilisateurService.saveUtilisateur(utilisateurForm, result);
		return "redirect:/";
	}
}
