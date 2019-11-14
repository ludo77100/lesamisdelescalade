package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ludo.entities.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{

}
