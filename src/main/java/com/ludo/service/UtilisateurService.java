package com.ludo.service;

import javax.validation.Valid;

import com.ludo.dto.UtilisateurDto;
import com.ludo.entities.Utilisateur;

public interface UtilisateurService {

	Utilisateur findByPseudo(String pseudo);

	Utilisateur findByEmail(String email);

	void saveUtilisateur(@Valid UtilisateurDto utilisateurDto);

}
