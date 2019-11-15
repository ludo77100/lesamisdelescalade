package com.ludo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ludo.dao.SiteEscaladeRepository;
import com.ludo.entities.SiteEscalade;

@SpringBootApplication
public class LesamisdelescaladeApplication {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LesamisdelescaladeApplication.class, args);
		SiteEscaladeRepository siteEscaladeRepository=ctx.getBean(SiteEscaladeRepository.class);
		siteEscaladeRepository.save(new SiteEscalade("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		siteEscaladeRepository.save(new SiteEscalade("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		siteEscaladeRepository.save(new SiteEscalade("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		siteEscaladeRepository.save(new SiteEscalade("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		siteEscaladeRepository.save(new SiteEscalade("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		
		siteEscaladeRepository.findAll().forEach(p->System.out.println(p.getNom()));
	}
	}

