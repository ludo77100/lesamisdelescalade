package com.ludo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


import com.ludo.entities.Topo;

/**
 * Couche DAO topo pour l'application
 * @author A87671
 *
 */
@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

	/**
	 * Pour lister tous les topo appartenant à un utilisateur
	 * @param utilDet utilisateur pour lequel on veut trouver les topos
	 * @return une liste de topo
	 */
	@Query("select t from Topo t where t.utilisateur like :x")
	List<Topo> findAllByUser(@Param("x")UserDetails utilDet);

	/**
	 * Pour lister tous les topos disponible, avec exclusion des topos appartenant à l'utilisateur en cours,
	 * et d'effectuer une recherche par le nom du topo ou le nom du spot.
	 * @param mc mot clé pour la recherche
	 * @param pageable instance pour les informations de pagination
	 * @param pseudo le pseudo de l'utilisateur en cours, pour l'exclusion de la liste de résultats des topos lui appartenant.
	 * @return la liste de topos
	 */
	@Query("select t from Topo t where (t.nom like :x or t.spotNom like :x ) and (t.disponible = true and t.utilisateur.pseudo not like :pseudo)")
	public Page<Topo> chercher(@Param("x")String mc, Pageable pageable, @Param("pseudo")String pseudo);
}
