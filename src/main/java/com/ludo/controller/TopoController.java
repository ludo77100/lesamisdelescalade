package com.ludo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ludo.entities.Utilisateur;

@Controller
public class TopoController {

	@GetMapping("/topo")
	public String topo(Model model) {
		
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("utilDet", utilDet);
		
		return "/topo" ;
	}
}
