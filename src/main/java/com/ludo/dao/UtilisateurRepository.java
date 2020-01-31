package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	Utilisateur findByPseudo (String pseudo);
	Utilisateur findByEmail(String email);


}
