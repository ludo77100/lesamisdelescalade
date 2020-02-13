package com.ludo.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Utilisateur;
import com.ludo.enums.RoleEnum;
import com.ludo.service.UtilisateurService;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Autowired
	UtilisateurService utilisateurService ;


	@Autowired
	public WebMvcConfig(UtilisateurService utilisateurService) {
		//Ceci n'est pas à recopier en prod
		if (utilisateurService.findByPseudo("admin") == null) {
			List<RoleEnum> userRole = Collections.singletonList(RoleEnum.USER);
			List<RoleEnum> adminRole = Arrays.asList(RoleEnum.USER, RoleEnum.ADMINISTRATOR);

			Utilisateur user = new Utilisateur("user", "user", "User", userRole);
			Utilisateur adminUser = new Utilisateur("admin", "admin", "Admin", adminRole);
		
			
			utilisateurService.save(user);
			utilisateurService.save(adminUser);
		}
	}
}

