package com.ludo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.dao.TopoRepository;
import com.ludo.entities.Reservation;
import com.ludo.entities.Spot;
import com.ludo.entities.Topo;
import com.ludo.entities.Utilisateur;
import com.ludo.service.ReservationService;
import com.ludo.service.SpotService;
import com.ludo.service.TopoService;

/**
 * Controlleur pour la partie topo de l'application
 * @author A87671
 *
 */

@Controller
public class TopoController {

	@Autowired
	private TopoRepository topoRepository ; // Pour la recherche
	@Autowired
	private TopoService topoService ;
	@Autowired
	private SpotService spotService ;
	@Autowired
	private ReservationService reservationService ;
	
	/**
	 * Controller qui renvoie la liste des topo et permet d'effectuer une recherche de topo
	 * @param model instance du model en cours
	 * @param p page en cours
	 * @param s le nombre de résultat par page
	 * @param mc mot clé pour la recherche
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return une vue de la liste des spots
	 */
	@GetMapping("/listeTopo")
	public String listeTopo(
			Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "15")int s,
			@RequestParam(name="mc", defaultValue = "")String mc,
			HttpServletRequest request) {
		

		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
			
			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String pseudo = utilDet.getUsername();
		
			Page<Topo> pageListeTopo = 
				topoRepository.chercher("%"+mc+"%", PageRequest.of(p, s), pseudo);
		model.addAttribute("listeTopo", pageListeTopo.getContent());
		int[] pages = new int[pageListeTopo.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);

		return "listeTopo";
		}
	}
	
	/**
	 * Controller qui permet d'afficher la vue topo
	 * @param model instance du model en cours
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return la vue topo
	 */
	@GetMapping("/topo")
	public String topo(
			Model model, 
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {
		
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("utilDet", utilDet);
		
		//Ajout de Topo
		model.addAttribute("topo", new Topo());
		
		//liste des spot pour liste déroulante dans le formulaire de saisie de Topo
		List <Spot> listeSpotForm = spotService.findAll();
		model.addAttribute("listeSpotForm", listeSpotForm);
		
		//Liste des topos qui appartiennent à l'utilisateur
		List <Topo> listeTopo = topoService.findAllByUser(utilDet) ;
		model.addAttribute("listeTopo", listeTopo);
		
		//Liste des demandes de réservations en attente
		List <Reservation> listeDemandeReservation = reservationService.findByProprietaireAttente(utilDet.getUsername());
		model.addAttribute("listeDemandeReservation", listeDemandeReservation);
		
		//Liste des topo réservés
		List <Reservation> listeTopoReserve = reservationService.findByProprietaireReserve(utilDet.getUsername());
		model.addAttribute("listeTopoReserve", listeTopoReserve);
		
		//Liste des topo que l'utilisateur en cours à reserve pour son compte
		List<Reservation> listeTopoPrete = reservationService.findByPrete(utilDet.getUsername());
		model.addAttribute("listeTopoPrete", listeTopoPrete);
		return "/topo" ;
		}
	}
	
	/**
	 * Controller pour l'action du bouton sauvegarder d'un nouveau topo
	 * @param model instance du model en cour
	 * @param topo instance du topo à ajouter
	 * @param result resultat du binding pour gérer les erreurs de saisies
	 * @return la vue topo
	 */
	@PostMapping("/saveTopo")
	public String saveTopo(
			Model model, 
			@Valid Topo topo, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/topo";
		}
		
		topoService.saveTopo(topo);
		
		return "redirect:/topo";	
	}
	
	/////////////////////////SUPPRESSION TOPO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour la suppression d'un topo
	 * @param topoId id du topo à supprimé
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return le vue topo
	 */
	@GetMapping("/deleteTopo/{topoId}")
	public String deleteTopo(
			@PathVariable("topoId") Long topoId,
			HttpServletRequest request) {

		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Topo topo = topoService.findById(topoId).get(); // On récupère le topo en cours

			if (utilDet.getUsername().equals(topo.getUtilisateur().getUsername())
					|| utilDet.getAuthorities().toString().contains("ADMINISTRATOR")) {
				topoService.deleteById(topoId); // On supprime le topo
				return "redirect:/topo";
			} else {
				return "redirect:/topo";
			}
		}
	}
	
	/////////////////////////DISPONIBILITE DU TOPO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * Controller pour changer la disponibilité d'un topo
	 * @param topoId id du topo
	 * @param request HttpServletRequest, ici pour vérifier qu'un utilisateur est connecté
	 * @return la vue topo
	 */
	@GetMapping("/topo/changerDispoTopo/{topoId}")
	public String changerDispoTopo(
			@PathVariable("topoId")Long topoId,
			HttpServletRequest request) {
		
		if (request.getRemoteUser() == null) {
			return "formConnexion";
		} else {

			UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			Topo topo = topoService.findById(topoId).get(); // On récupère le topo en cours
			
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
