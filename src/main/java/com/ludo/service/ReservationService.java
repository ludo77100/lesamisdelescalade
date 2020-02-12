package com.ludo.service;

import java.util.List;
import java.util.Optional;

import com.ludo.entities.Reservation;
import com.ludo.entities.Topo;

public interface ReservationService {

	void enregistrerDemandeReservation(Long topoId, Topo topo);

	Optional<Reservation> findById(Long reservationId);

	void save(Reservation reservation);

	void delete(Reservation reservation);

	List<Reservation> findByProprietaireAttente(String username);

	List<Reservation> findByProprietaireReserve(String username);

	List<Reservation> findByPrete(String username);

}
