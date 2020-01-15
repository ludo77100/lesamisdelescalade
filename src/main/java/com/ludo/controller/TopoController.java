package com.ludo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.ReservationRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.dao.TopoRepository;
import com.ludo.entities.Reservation;
import com.ludo.entities.Spot;
import com.ludo.entities.Topo;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.TopoForm;
import com.ludo.metier.TopoService;

@Controller
public class TopoController {

	@Autowired
	TopoService topoService ;
	@Autowired
	SpotRepository spotRepository ;
	@Autowired
	TopoRepository topoRepository ;
	@Autowired
	ReservationRepository reservationRepository ;
	/*
	 * Controller qui renvoie la liste des spots
	 */
	@GetMapping("/listeTopo")
	public String listeTopo(
			Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "15")int s,
			@RequestParam(name="mc", defaultValue = "")String mc) {
		
		/*
		 * UserDetails utilDet = (Utilisateur)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); String
		 * pseudoUtilCo = utilDet.getUsername() ;
		 */
		
		Page<Topo> pageListeTopo = 
				topoRepository.chercher("%"+mc+"%", PageRequest.of(p, s));
		model.addAttribute("listeTopo", pageListeTopo.getContent());
		int[] pages = new int[pageListeTopo.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);

	
		return "listeTopo";
		
	}
	
	@GetMapping("/topo")
	public String topo(Model model) {
		
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("utilDet", utilDet);
		
		//Ajout de Topo
		model.addAttribute("Topo", new Topo());
		
		//liste des spot pour liste déroulante dans le formulaire de saisie de Topo
		List <Spot> listeSpotForm = spotRepository.findAll();
		model.addAttribute("listeSpotForm", listeSpotForm);
		
		//Liste des topos qui appartiennent à l'utilisateur
		List <Topo> listeTopo = topoRepository.findAllByUser(utilDet) ;
		model.addAttribute("listeTopo", listeTopo);
		
		//Liste des demandes de réservations en attente
		List <Reservation> listeDemandeReservation = reservationRepository.findByProprietaireAttente(utilDet.getUsername());
		model.addAttribute("listeDemandeReservation", listeDemandeReservation);
		
		//Liste des topo réservés
		List <Reservation> listeTopoReserve = reservationRepository.findByProprietaireReserve(utilDet.getUsername());
		model.addAttribute("listeTopoReserve", listeTopoReserve);
		return "/topo" ;
	}
	
	@PostMapping("/saveTopo")
	public String saveTopo(Model model, @ModelAttribute("topoForm") TopoForm topoForm, final RedirectAttributes redirect) {
		
		topoService.saveTopo(topoForm);
		
		return "redirect:/topo";
		
	}
	
/////////////////////////SUPPRESSION TOPO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@GetMapping("/deleteTopo/{topoId}")
	public String deleteTopo(@PathVariable("topoId") Long topoId, final RedirectAttributes redirect,
			HttpServletRequest request) {

		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Topo topo = topoRepository.findById(topoId).get(); // On récupère le topo en cours

			if (utilDet.getUsername().equals(topo.getUtilisateur().getUsername())
					|| utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				topoRepository.deleteById(topoId); // On supprime le topo
				return "redirect:/topo";
			} else {
				return "redirect:/topo";
			}
		}
	}
	
	/////////////////////////DISPONIBILITE DU TOPO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/*
	 * Cette méthode permet de changer la disponibilité d'un topo
	 */
	@GetMapping("/topo/changerDispoTopo/{topoId}")
	public String changerDispoTopo(
			@PathVariable("topoId")Long topoId,
			final RedirectAttributes redirect,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Topo topo = topoRepository.findById(topoId).get(); // On récupère le topo en cours
			
			if (utilDet.getUsername().equals(topo.getUtilisateur().getUsername())
					|| utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				topoService.changerDispoTopo(topoId);
				return "redirect:/topo";
			} else {
				return "redirect:/topo";
	}
}
	}
	
}
