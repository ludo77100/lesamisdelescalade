package com.ludo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topo implements Serializable {
	@Id
	@GeneratedValue
	private Long idTopo;
}
