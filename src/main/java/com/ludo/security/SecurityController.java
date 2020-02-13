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
	 * l'accès à ce formulaire est bloqué si l'utilisateur est déjà authentifier
	 */
	@GetMapping("/connexionUtilisateur")
	public ModelAndView connexionUtilisateur(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("formConnexion");
	}
	
	/*
	 * Renvoie une page informant le visiteur qu'il n'a pas accès à la ressource demandée
	 */
	@RequestMapping(value = "/403")
	public String accesInterdit() {
		return "403";
	}
	
	/*
	 * Renvoie une page informant le visiteur que la ressource à laquelle il tente d'accéder n'existe pas
	 */
	@RequestMapping(value = "/404")
	public String pageIntrouvable() {
		return "404";
	}
	
}
