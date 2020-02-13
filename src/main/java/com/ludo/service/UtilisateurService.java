package com.ludo.service;

import javax.validation.Valid;

import com.ludo.dto.UtilisateurDto;
import com.ludo.entities.Utilisateur;

/**
 * Couche Service utilisateur pour l'application
 * @author A87671
 *
 */
public interface UtilisateurService {

	/**
	 * Pour trouver un utilisateur par son pseudo
	 * @param pseudo pseudo de l'utilisateur recherché
	 * @return un utilisateur 
	 */
	Utilisateur findByPseudo(String pseudo);
	
	/**
	 * Pour trouver un utilisateur par son email
	 * @param email email de l'utilisateur recherché
	 * @return un utilisateur 
	 */
	Utilisateur findByEmail(String email);

	/**
	 * Pour sauvegarder un nouvel utilisateur
	 * @param utilisateurDto instance de l'utilisateur en cours de sauvegarde
	 */
	void saveUtilisateur(@Valid UtilisateurDto utilisateurDto);

	void save(Utilisateur user);

}
