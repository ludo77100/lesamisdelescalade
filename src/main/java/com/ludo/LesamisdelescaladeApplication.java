package com.ludo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


/**
 * Application les amis de l'escalade ! Projet 6 OCR !
 * @author A87671
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LesamisdelescaladeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LesamisdelescaladeApplication.class, args);

	}
	}

