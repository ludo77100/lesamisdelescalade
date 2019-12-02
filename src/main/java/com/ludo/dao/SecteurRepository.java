package com.ludo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long>{
	
	@Query("select s from Secteur s where s.spot.idSiteEscalade like :x")
	public List<Secteur> findBySpot(@Param("x")Long spotId);
}
