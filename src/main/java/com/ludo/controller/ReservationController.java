package com.ludo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ludo.entities.Reservation;
import com.ludo.entities.Topo;
import com.ludo.service.ReservationService;
import com.ludo.service.TopoService;

/**
 * Controller pour la partie réservation de topo
 * @author A87671
 *
 */
@Controller
public class ReservationController {
	
	@Autowired
	ReservationService reservationService ;
	@Autowired
	private TopoService topoService ;
	
	/**
	 * Controller pour enregistrer une demande de réservation de topo
	 * @param topoId id du topo qui est demandé pour la réservation
	 * @return la liste des topos 
	 */
	@GetMapping("/listeTopo/reserverTopo/{topoId}")
	public String demandeReservationTopo(@PathVariable("topoId")Long topoId) {
		
		Topo topo = topoService.findById(topoId).get();
		
		if (!topo.isDisponible()) {
			return "listeTopo" ;
		} else {

			reservationService.enregistrerDemandeReservation(topoId);
			
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
		
		Reservation reservation = reservationService.findById(reservationId).get();
		Topo topo = topoService.findById(reservation.getTopo().getIdTopo()).get();
		
		reservation.setEtatDemande(true);
		reservationService.save(reservation);
		
		topoService.changerDispoTopo(topo.getIdTopo());
				
		return "redirect:/topo" ;
		
	}

	/**
	 * Permet de terminer une réservation
	 * @param reservationId id de la réservation
	 * @return la vue topo
	 */
	@GetMapping("/topo/terminerReservation/{reservationId}")
	public String terminerReservation(@PathVariable("reservationId")Long reservationId) {
	
		Reservation reservation = reservationService.findById(reservationId).get();
		Topo topo = topoService.findById(reservation.getTopo().getIdTopo()).get();
		
		reservationService.delete(reservation);
		
		topoService.changerDispoTopo(topo.getIdTopo());

	return "redirect:/topo" ;
	}
}
