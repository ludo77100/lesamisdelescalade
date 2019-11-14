package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.SiteEscalade;

@Repository
public interface SiteEscaladeRepository extends JpaRepository<SiteEscalade, Long>{

}
