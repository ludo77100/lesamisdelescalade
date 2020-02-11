package com.ludo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Utilisateur;

@Service
public class UtilisateurDetailsImpl implements UserDetailsService {
	
	private UtilisateurRepository utilisateurRepository ;
	
    @Autowired
    public UtilisateurDetailsImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
        if (pseudo == null) {
            throw new UsernameNotFoundException("No user present with username : " + pseudo);
        }
        else {
            return utilisateur;
        }
    }
}
