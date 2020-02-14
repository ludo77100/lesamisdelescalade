package com.ludo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Reservation;

/**
 * Couche DAO reservation pour l'application
 * @author A87671
 *
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	/**
	 * Pour lister les topos en attente de demande de réservation 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo en attente de réservation)
	 * @return liste de topos en attente de réservation
	 */
	@Query("select r from Reservation r where r.proprietaire like :x and etatDemande = false")
	List<Reservation> findByProprietaireAttente(@Param("x")String username);

	/**
	 * Pour lister les topos dont le propriétaire à accepeter la réservation
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qu'il à prêté)
	 * @return liste de topos prêté par l'utilisateur (ceux dont il à accepter la réservation)
	 */
	@Query("select r from Reservation r where r.proprietaire like :x and etatDemande = true")
	List<Reservation> findByProprietaireReserve(@Param("x")String username);

	/**
	 * Pour lister les topo qu'un utilisateur s'est fait prêter 
	 * @param username pseudo de l'utilisateur (pour qui on veut lister les topo qui lui sont prêté)
	 * @return liste de topos prete à un utilisateur
	 */
	@Query("select r from Reservation r where r.reservant like :x and etatDemande = true")
	List<Reservation> findByPrete(@Param("x")String username);
}
