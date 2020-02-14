package com.ludo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;

import com.ludo.entities.Topo;

/**
 * Couche Service topo pour l'application
 * @author A87671
 *
 */
public interface TopoService {

	/**
	 * Pour trouver un topo par son id
	 * @param topoId id du topo
	 * @return un topo
	 */
	Optional<Topo> findById(Long topoId);

	/**
	 * Pour sauvegarder un nouveau topo
	 * @param topo instance du topo à sauvegarder
	 */
	void saveTopo(Topo topo);

	/**
	 * Pour lister les topos appartenant à un utilisateur
	 * @param utilDet instance de l'utilisateur en cours
	 * @return une liste de topos
	 */
	List<Topo> findAllByUser(UserDetails utilDet);

	/**
	 * Pour supprimer un topo par son id
	 * @param topoId id du topo à supprimer
	 */
	void deleteById(Long topoId);

	/**
	 * Pour switcher l'état de disponibilité d'un topo (disponible/non disponible)
	 * @param topoId id du topo
	 */
	void changerDispoTopo(Long topoId);

}
