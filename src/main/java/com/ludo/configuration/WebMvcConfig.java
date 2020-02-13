package com.ludo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{


	/*
	 * @Autowired public WebMvcConfig(UtilisateurRepository utilisateurRepository) {
	 * // Ceci n'est pas à recopier en production List<RoleEnum> userRole =
	 * Collections.singletonList(RoleEnum.USER); List<RoleEnum> adminRole =
	 * Arrays.asList(RoleEnum.USER, RoleEnum.ADMINISTRATOR); Utilisateur user = new
	 * Utilisateur("user", "user", "User", userRole); Utilisateur adminUser = new
	 * Utilisateur("admin", "admin", "Admin", adminRole);
	 * 
	 * utilisateurRepository.save(user); utilisateurRepository.save(adminUser); }
	 */

	 
}
