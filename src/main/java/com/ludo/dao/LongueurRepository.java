package com.ludo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ludo.entities.Longueur;

public interface LongueurRepository extends JpaRepository<Longueur, Long>{

	@Query("select l from Longueur l where l.voie.idVoie like :x")
	public List<Longueur> findByVoie(@Param("x")Long voieId);

}
