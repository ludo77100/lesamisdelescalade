package com.ludo.service;

import java.util.List;
import java.util.Optional;

import com.ludo.entities.Reservation;

public interface ReservationService {

	/**
	 * Pour sauvegarder une nouvelle demannde de réservation
	 * @param topoId id du topo à réserver
	 */
	void enregistrerDemandeReservation(Long topoId);

	/**
	 * Pour trouver une réservation par son id
	 * @param reservationId id de la réservation
	 * @return une réservation
	 */
	Optional<Reservation> findById(Long reservationId);

	/**
	 * Pour sauvergarder les modifications apporté à une réservation existante
	 * @param reservation instance de la reservation à sauvegarder
	 */
	void save(Reservation reservation);

	/**
	 * Pour supprimer une reservation
	 * @param reservation instance de la reservation à supprimer 
	 */
	void delete(Reservation reservation);

	/**
	 * Pour lister les topos en attente de demande de réservation 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo en attente de réservation)
	 * @return liste de topos en attente de réservation
	 */
	List<Reservation> findByProprietaireAttente(String username);

	/**
	 * Pour lister les topos dont le propriétaire à accepeter la réservation
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qu'il à prêté)
	 * @return liste de topos prêté par l'utilisateur (ceux dont il à accepter la réservation)
	 */
	List<Reservation> findByProprietaireReserve(String username);

	/**
	 * Pour lister les topo qu'un utilisateur s'est fait prêter 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qui lui sont prêté)
	 * @return liste de topos prete à un utilisateur
	 */
	List<Reservation> findByPrete(String username);

}
