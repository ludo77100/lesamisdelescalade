package com.ludo.metier;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

@Service
public class SiteEscaladeMetier {
	private Long idSiteEscalade ;

	private String adresse ;

	private String commune ;

	private String region ;

	private String departement ;
	
	private String nom ;
}
