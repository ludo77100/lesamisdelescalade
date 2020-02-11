package com.ludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ludo.dao.ReservationRepository;
import com.ludo.dao.TopoRepository;
import com.ludo.entities.Reservation;
import com.ludo.entities.Topo;
import com.ludo.service.ReservationService;

/**
 * Controller pour la partie réservation de topo
 * @author A87671
 *
 */
@Controller
public class ReservationController {
	
	@Autowired
	TopoRepository topoRepository ;
	@Autowired
	ReservationRepository reservationRepository ;
	@Autowired
	ReservationService reservationService ;
	
	/**
	 * Controller pour enregistrer une demande de réservation de topo
	 * @param topoId id du topo qui est demandé pour la réservation
	 * @return la liste des topos 
	 */
	@GetMapping("/listeTopo/reserverTopo/{topoId}")
	public String demandeReservationTopo(@PathVariable("topoId")Long topoId) {
		
		Topo topo = topoRepository.findById(topoId).get();
		
		if (!topo.isDisponible()) {
			return "listeTopo" ;
		} else {

			reservationService.enregistrerDemandeReservation(topoId, topo);
			
		}
		return "redirect:/listeTopo" ;
	}
	
	/**
	 * Permet d'accepter uen demande de réservation
	 * @param reservationId id de la réservation
	 * @return la vue topo
	 */
	@GetMapping("/topo/accepterReservation/{reservationId}")
	public String accepterReservation(@PathVariable("reservationId")Long reservationId) {
		
		Reservation reservation = reservationRepository.findById(reservationId).get();
		Topo topo = topoRepository.findById(reservation.getTopo().getIdTopo()).get();
		
		reservation.setEtatDemande(true);
		reservationRepository.save(reservation);
		
		topo.setDisponible(false);
		topoRepository.save(topo);
				
		return "redirect:/topo" ;
		
	}

	/**
	 * Permet de terminer une réservation
	 * @param reservationId id de la réservation
	 * @return la vue topo
	 */
	@GetMapping("/topo/terminerReservation/{reservationId}")
	public String terminerReservation(@PathVariable("reservationId")Long reservationId) {
	
		Reservation reservation = reservationRepository.findById(reservationId).get();
		Topo topo = topoRepository.findById(reservation.getTopo().getIdTopo()).get();
		
		reservationRepository.delete(reservation);
		
		if (!topo.isDisponible()){
			topo.setDisponible(true);
			topoRepository.save(topo);
		}

	return "redirect:/topo" ;
	}
}
