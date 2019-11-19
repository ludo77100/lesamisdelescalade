package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Voie;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Long> {

}
