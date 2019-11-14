package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Longueur implements Serializable {
	@Id @GeneratedValue
	private Long idLongueur ;

}
