package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Secteur;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long>{
}
