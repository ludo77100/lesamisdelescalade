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

}

