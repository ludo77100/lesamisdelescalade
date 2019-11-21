package com.ludo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.SiteEscalade;

@Repository
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{
	
	@Query("select p from SiteEscalade p where p.nom like :x")
	public Page<SiteEscalade> chercher(@Param("x")String mc, Pageable pageable);
 
}
