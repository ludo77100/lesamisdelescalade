package com.ludo.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@GetMapping("/connexionUtilisateur")
	public String connexionUtilisateur() {
		return "formConnexion";
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
