package com.ludo.dao;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Spot;

/**
 * Couche DAO spot pour l'application
 * @author A87671
 *
 */
@Repository
public interface SpotRepository extends JpaRepository<Spot, Long>{

	/**
	 * Pour lister tous les spot, permet également d'effectuer une recherce dans le nom ou le lieu du spot
	 * @param mc mot clé pour la recherche
	 * @param pageable instance pour les informations de pagination
	 * @return une liste de spot
	 */
	@Query("select s from Spot s where s.nom like :x or s.localite like :x")
	public Page<Spot> chercher(@Param("x")String mc, Pageable pageable);

	
	/**
	 * Pour trouver un spot par son nom
	 * @param spotNom nom du spot
	 * @return le spot par son nom
	 */
	@Query("select s from Spot s where s.nom like :x")
	public Spot findSpotByName(@Param("x")String spotNom);
}
