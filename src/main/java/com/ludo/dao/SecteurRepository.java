package com.ludo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Secteur;

/**
 * Couche DAO secteur pour l'application
 * @author A87671
 *
 */
@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long>{

	/**
	 * Pour lister les secteur lié à un spot
	 * @param spotId id du spot pour leqeul on veut trouver les secteur
	 * @return une liste de secteur
	 */
	@Query("select s from Secteur s where s.spot.idSiteEscalade like :x")
	public List<Secteur> findBySpot(@Param("x")Long spotId);
}
