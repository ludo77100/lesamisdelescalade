package com.ludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ludo.dao.SpotRepository;
import com.ludo.entities.Spot;
import com.ludo.forms.CommentaireForm;
import com.ludo.metier.CommentaireService;

@Controller
public class CommentaireController {
	
	@Autowired
	SpotRepository spotRepository ;
	@Autowired
	CommentaireService commentaireService ;
	
	@GetMapping("/ajoutCommentaire/{spotId}")
	public String ajoutCommentaire(Model model,
			@PathVariable("spotId")Long spotId) {
			Spot spot = spotRepository.findById(spotId).get();
			
			model.addAttribute("spotId", spot.getIdSiteEscalade());
			
		return "formCommentaire" ;
	}
	
	@PostMapping("/saveCommentaire/{spotId}")
	public String saveCommentaire(
			@ModelAttribute("commentaireForm")CommentaireForm commentaireForm,
			@PathVariable("spotId")Long spotId) {
		
		commentaireService.saveCommentaire(commentaireForm, spotId);
		
		return "redirect:/spot/" +spotId ;
	}
}
