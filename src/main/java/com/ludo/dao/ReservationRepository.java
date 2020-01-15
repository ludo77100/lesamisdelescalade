package com.ludo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select r from Reservation r where r.proprietaire like :x and etatDemande = false")
	List<Reservation> findByProprietaireAttente(@Param("x")String username);

	@Query("select r from Reservation r where r.proprietaire like :x and etatDemande = true")
	List<Reservation> findByProprietaireReserve(@Param("x")String username);
	

}
