package com.ludo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.ReservationRepository;
import com.ludo.dao.TopoRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Reservation;
import com.ludo.entities.Topo;
import com.ludo.entities.Utilisateur;

@Service
public class ReservationService {
	
	@Autowired
	UtilisateurRepository utilisateurRepository ;
	@Autowired
	TopoRepository topoRepository ;
	@Autowired
	ReservationRepository reservationRepository ;

	public void enregistrerDemandeReservation(Long topoId, Topo topo) {
		
		Date date = new Date() ; 
		Reservation reservationTopo = new Reservation();
		UserDetails utilDet = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(utilDet.getUsername());
		
		reservationTopo.setUtilisateur(utilisateur);
		reservationTopo.setReservant(utilisateur.getUsername());
		reservationTopo.setEtatDemande(false);
		reservationTopo.setProprietaire(topoRepository.findById(topoId).get().getUtilisateur().getUsername());
		reservationTopo.setDateDemande(date);
		reservationTopo.setTopo(topoRepository.findById(topoId).get());
		
		reservationRepository.save(reservationTopo);
	}

}
