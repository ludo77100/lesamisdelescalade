package com.ludo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Commentaire;
import com.ludo.entities.Secteur;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{

	/* 
	 * Retourne la liste des secteurs lié à un spot d'escalade par le biais de l'id du spot d'escalade 
	 */
	@Query("select c from Commentaire c where c.spot.idSiteEscalade like :x")
	public List<Commentaire> findCommentaireBySpot(@Param("x")Long spotId);
	

}
