package com.ludo.service.impl;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.dto.UtilisateurDto;
import com.ludo.entities.Utilisateur;
import com.ludo.service.UtilisateurService;

/**
 * Implémentation Service Utilisateur pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	
	/**
	 * Pour trouver un utilisateur par son pseudo
	 * @param pseudo pseudo de l'utilisateur recherché
	 * @return un utilisateur 
	 */
	@Override
	public Utilisateur findByPseudo(String pseudo) {
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
		return utilisateur ;
	}

	/**
	 * Pour trouver un utilisateur par son email
	 * @param email email de l'utilisateur recherché
	 * @return un utilisateur 
	 */
	@Override
	public Utilisateur findByEmail(String email) {
		Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
		return utilisateur ;
	}

	/**
	 * Pour sauvegarder un nouvel utilisateur
	 * @param utilisateurDto instance de l'utilisateur en cours de sauvegarde
	 */
	@Override
	public void saveUtilisateur(@Valid UtilisateurDto utilisateurDto) {
		
		Utilisateur newUtilisateur = new Utilisateur();
		
		newUtilisateur.setPseudo(utilisateurDto.getPseudo());
		newUtilisateur.setMotDePass(utilisateurDto.getMotDePass());
		newUtilisateur.setEmail(utilisateurDto.getEmail());
		
		utilisateurRepository.save(newUtilisateur);		
	}

	@Override
	public void save(Utilisateur user) {
		utilisateurRepository.save(user);
		
	}

}
