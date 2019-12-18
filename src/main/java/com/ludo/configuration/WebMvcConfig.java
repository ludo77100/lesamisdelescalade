package com.ludo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Utilisateur;
import com.ludo.enums.RoleEnum;


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
	 * 
	 */
}
