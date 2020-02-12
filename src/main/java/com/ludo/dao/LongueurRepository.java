package com.ludo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Longueur;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Long>{

	/**
	 * Pour obtenir une liste de longueur
	 * @param voieId id de la voie
	 * @return une liste de longueur lié à une voie
	 */
	@Query("select l from Longueur l where l.voie.idVoie like :x")
	public List<Longueur> findByVoie(@Param("x")Long voieId);
}
