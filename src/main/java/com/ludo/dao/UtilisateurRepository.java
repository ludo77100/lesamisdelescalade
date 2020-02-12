package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	/**
	 * Pour trouver un utilisateur par son pseudo
	 * @param pseudo de l'utilisateur recherché
	 * @return l'utilisateur
	 */
	Utilisateur findByPseudo (String pseudo);
	
	/**
	 * Pour trouver un utilisateur par son email
	 * @param email de l'utilisateur recherché
	 * @return l'utilisateur
	 */
	Utilisateur findByEmail(String email);
}
