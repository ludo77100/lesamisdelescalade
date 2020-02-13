package com.ludo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.ReservationRepository;
import com.ludo.dao.TopoRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Reservation;
import com.ludo.entities.Utilisateur;
import com.ludo.service.ReservationService;

/**
 * Implémentation Service réservation pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository ;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	@Autowired
	private TopoRepository topoRepository ;

	/**
	 * Pour sauvegarder une nouvelle demannde de réservation
	 * @param topoId id du topo à réserver
	 */
	@Override
	public void enregistrerDemandeReservation(Long topoId) {
		
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

	/**
	 * Pour trouver une réservation par son id
	 * @param reservationId id de la réservation
	 * @return une réservation
	 */
	@Override
	public Optional<Reservation> findById(Long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		return reservation ;
	}

	/**
	 * Pour sauvergarder les modifications apporté à une réservation existante
	 * @param reservation instance de la reservation à sauvegarder
	 */
	@Override
	public void save(Reservation reservation) {
		reservationRepository.save(reservation);		
	}

	/**
	 * Pour supprimer une reservation
	 * @param reservation instance de la reservation à supprimer 
	 */
	@Override
	public void delete(Reservation reservation) {
		reservationRepository.delete(reservation);
	}

	/**
	 * Pour lister les topos en attente de demande de réservation 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo en attente de réservation)
	 * @return liste de topos en attente de réservation
	 */
	@Override
	public List<Reservation> findByProprietaireAttente(String username) {
		List<Reservation> reservationsAttente = reservationRepository.findByProprietaireAttente(username);
		return reservationsAttente;
	}

	/**
	 * Pour lister les topos dont le propriétaire à accepeter la réservation
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qu'il à prêté)
	 * @return liste de topos prêté par l'utilisateur (ceux dont il à accepter la réservation)
	 */
	@Override
	public List<Reservation> findByProprietaireReserve(String username) {
		List<Reservation> reservationsReserve = reservationRepository.findByProprietaireReserve(username);
		return reservationsReserve ;
	}

	/**
	 * Pour lister les topo qu'un utilisateur s'est fait prêter 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qui lui sont prêté)
	 * @return liste de topos prete à un utilisateur
	 */
	@Override
	public List<Reservation> findByPrete(String username) {
		List<Reservation> reservationsPrete = reservationRepository.findByPrete(username);
		return reservationsPrete;
	}
}
