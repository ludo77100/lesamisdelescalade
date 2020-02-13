package com.ludo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ludo.dao.SpotRepository;
import com.ludo.dao.TopoRepository;
import com.ludo.dao.UtilisateurRepository;
import com.ludo.entities.Spot;
import com.ludo.entities.Topo;
import com.ludo.entities.Utilisateur;
import com.ludo.service.TopoService;

/**
 * Implémentation Service topo pour l'application
 * @author A87671
 *
 */
@Service
@Transactional
public class TopoServiceImpl implements TopoService{

	@Autowired
	private TopoRepository topoRepository ;
	@Autowired
	private SpotRepository spotRepository ;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;

	/**
	 * Pour trouver un topo par son id
	 * @param topoId id du topo
	 * @return un topo
	 */
	@Override
	public Optional<Topo> findById(Long topoId) {
		Optional<Topo> topo = topoRepository.findById(topoId);
		return topo;
	}

	/**
	 * Pour sauvegarder un nouveau topo
	 * @param topo instance du topo à sauvegarder
	 */
	@Override
	public void saveTopo(Topo topo) {
		Topo newTopo = new Topo();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		Spot spot = spotRepository.findSpotByName(topo.getSpotNom());
		
		newTopo.setNom(topo.getNom());
		newTopo.setDisponible(false);
		newTopo.setDateParution(topo.getDateParution());
		newTopo.setDescription(topo.getDescription());
		newTopo.setLieu(topo.getLieu());
		newTopo.setUtilisateur(utilisateur);
		newTopo.setSpot(spot);
		newTopo.setSpotNom(topo.getSpotNom());
		
		topoRepository.save(newTopo);
	}

	/**
	 * Pour lister les topos appartenant à un utilisateur
	 * @param utilDet instance de l'utilisateur en cours
	 * @return une liste de topos
	 */
	@Override
	public List<Topo> findAllByUser(UserDetails utilDet) {
		List<Topo> topos = topoRepository.findAllByUser(utilDet);
		return topos ;
	}

	/**
	 * Pour supprimer un topo par son id
	 * @param topoId id du topo à supprimer
	 */
	@Override
	public void deleteById(Long topoId) {
		topoRepository.deleteById(topoId);
	}

	/**
	 * Pour switcher l'état de disponibilité d'un topo (disponible/non disponible)
	 * @param topoId id du topo
	 */
	@Override
	public void changerDispoTopo(Long topoId) {
		Topo topoDispo = topoRepository.findById(topoId).get();

		if (!topoDispo.isDisponible()) {
			topoDispo.setDisponible(true);
		} else {
			topoDispo.setDisponible(false);
		}
		topoRepository.save(topoDispo);
	}
	
	
	
}
