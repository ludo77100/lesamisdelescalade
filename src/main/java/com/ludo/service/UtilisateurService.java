package com.ludo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.dto.UtilisateurDto;
import com.ludo.entities.Utilisateur;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository ;


	public void saveUtilisateur(UtilisateurDto utilisateurDto) {
		Utilisateur newUtilisateur = new Utilisateur();
		
		newUtilisateur.setPseudo(utilisateurDto.getPseudo());
		newUtilisateur.setMotDePass(utilisateurDto.getMotDePass());
		newUtilisateur.setEmail(utilisateurDto.getEmail());
		
		utilisateurRepository.save(newUtilisateur);
	}
	
}
