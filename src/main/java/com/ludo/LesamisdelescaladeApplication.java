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
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		spotRepository.save(new Spot("Escalade 1", "ZZ", "IDF/Seine et Marne/Meaux"));
		
		spotRepository.findAll().forEach(p->System.out.println(p.getNom()));
	}
	}

