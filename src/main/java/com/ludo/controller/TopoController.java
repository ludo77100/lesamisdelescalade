package com.ludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ludo.entities.Topo;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.TopoForm;
import com.ludo.metier.TopoService;

@Controller
public class TopoController {

	@Autowired
	TopoService topoService ;
	
	@GetMapping("/topo")
	public String topo(Model model) {
		
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("utilDet", utilDet);
		
		//Ajout de Topo
		model.addAttribute("Topo", new Topo());
		
		return "/topo" ;
	}
	
	@PostMapping("/saveTopo")
	public String saveTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm) {
		
		topoService.saveTopo(topoForm);
		
		return "/topo";
		
	}
}
