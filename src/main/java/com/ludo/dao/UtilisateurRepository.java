package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ludo.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

}
