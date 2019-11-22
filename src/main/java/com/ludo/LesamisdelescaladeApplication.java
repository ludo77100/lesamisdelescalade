package com.ludo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ludo.dao.SpotRepository;
import com.ludo.entities.Spot;

@SpringBootApplication
public class LesamisdelescaladeApplication {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LesamisdelescaladeApplication.class, args);
		SpotRepository spotRepository =ctx.getBean(SpotRepository.class);
		spotRepository.save(new Spot("rue du moulin", "Meaux", "IDF", "Seine et Marne", "Escalade 1"));
		spotRepository.save(new Spot("rue du pavé", "Poincy", "IDF", "Seine et Marne", "Escalade 2"));
		spotRepository.save(new Spot("rue de la mairie", "Varreddes", "IDF", "Seine et Marne", "Escalade 3"));
		spotRepository.save(new Spot("rue de rivoli", "Paris", "IDF", "Paris", "Escalade 4"));
		spotRepository.save(new Spot("rue de la poste", "Chauconin", "IDF", "Seine et Marne", "Escalade 5"));
		
		spotRepository.findAll().forEach(p->System.out.println(p.getNom()));
	}
	}

