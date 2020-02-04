package com.ludo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import com.ludo.entities.Topo;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

	@Query("select t from Topo t where t.utilisateur like :x")
	List<Topo> findAllByUser(@Param("x")UserDetails utilDet);

	
	@Query("select t from Topo t where (t.nom like :x or t.spotNom like :x ) and (t.disponible = true and t.utilisateur.pseudo not like :pseudo)")
	public Page<Topo> chercher(@Param("x")String mc, Pageable pageable, @Param("pseudo")String pseudo);
}
