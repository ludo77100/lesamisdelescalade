package com.ludo.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

	/*
	 * Controller qui permet d'afficher le formulaire de connexion
	 * l'accès à ce formulaire est blqué si l'utilisateur est déjà authentifier
	 */
	@GetMapping("/connexionUtilisateur")
	public ModelAndView connexionUtilisateur(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("formConnexion");
	}
	
	@RequestMapping(value = "/403")
	public String accesInterdit() {
		return "403";
	}
	@RequestMapping(value = "/404")
	public String pageIntrouvable() {
		return "404";
	}
	
}
