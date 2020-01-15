package com.ludo.dao;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Spot;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long>{
	
	/*
	 *  Retourne la liste des spot d'escalade sous forme de pagination 
	 *  Prend en charge le mot clé qui peut être saisie dans la barre de recherche (uniquement sur le nom du spot) 
	 */
	@Query("select p from Spot p where p.nom like :x")
	public Page<Spot> chercher(@Param("x")String mc, Pageable pageable);

	@Query("select s from Spot s where s.nom like :x")
	public Spot findSpotByName(@Param("x")String spotNom);
	
}
