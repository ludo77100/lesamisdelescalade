package com.ludo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Utilisateur;
import com.ludo.forms.UtilisateurForm;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository ;

	public void saveUtilisateur(UtilisateurForm utilisateurForm, BindingResult result) {
		Utilisateur newUtilisateur = new Utilisateur();
		
		newUtilisateur.setPseudo(utilisateurForm.getPseudo());
		newUtilisateur.setMotDePass(utilisateurForm.getMotDePass());
		newUtilisateur.setEmail(utilisateurForm.getEmail());
		newUtilisateur.setRole("USER");
		
		utilisateurRepository.save(newUtilisateur);
	}
	
	
	
}
