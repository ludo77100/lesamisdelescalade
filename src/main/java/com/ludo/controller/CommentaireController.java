package com.ludo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ludo.dao.CommentaireRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Commentaire;
import com.ludo.entities.Spot;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.CommentaireForm;
import com.ludo.forms.SpotForm;
import com.ludo.metier.CommentaireService;

@Controller
public class CommentaireController {
	
	@Autowired
	SpotRepository spotRepository ;
	@Autowired
	CommentaireRepository commentaireRepository ;
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
	
	@GetMapping("/spot/{spotId}/deleteCommentaire/{comId}")
	public String deleteCommentaire(
			@PathVariable("comId")Long comId,
			@PathVariable("spotId")Long spotId,
			HttpServletRequest request){
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
			Commentaire commentaire = commentaireRepository.findById(comId).get();
			
			if (utilDet.getUsername().equals(commentaire.getUtilisateur().getUsername()) || utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				commentaireRepository.deleteById(comId);
				return "redirect:/spot/" +spotId ;
			}
			return "redirect:/spot/" +spotId ;
		}
	}
	
	@GetMapping("/spot/{spotId}/editCommentaire/{comId}")
	public String editCommentaire(
			Model model,
			@PathVariable("comId")Long comId,
			@PathVariable("spotId")Long spotId) {
		
		Optional<Commentaire> c = commentaireRepository.findById(comId);
		Commentaire commentaire = null ;
		
		if (c.isPresent()) {
			commentaire = c.get();
		}
		model.addAttribute("commentaire", commentaire);
		model.addAttribute("spotId", spotId);
		
		return "editCommentaire" ;
	}
	
	@PostMapping("/spot/{spotId}/saveEditCommentaire/{comId}")
	public String saveEditCommentaire(
			@ModelAttribute("commentaireForm")CommentaireForm commentaireForm,
			@PathVariable("comId")Long comId,
			@PathVariable("spotId")Long spotId) {
		
		commentaireService.saveEditCommentaire(commentaireForm, comId);
		
		return "redirect:/spot/" +spotId ;
	}
}
