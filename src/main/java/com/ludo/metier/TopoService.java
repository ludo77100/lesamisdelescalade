package com.ludo.metier;

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
import com.ludo.forms.TopoForm;

@Service
public class TopoService {

	@Autowired
	private TopoRepository topoRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository ;
	@Autowired
	private SpotRepository spotRepository ;
	
	public void saveTopo(TopoForm topoForm) {

		Topo newTopo = new Topo();
		
		UserDetails util = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur utilisateur = utilisateurRepository.findByPseudo(util.getUsername());
		
		Spot spotx = spotRepository.findSpotByName(topoForm.getSpotNom());
		
		newTopo.setNom(topoForm.getNom());
		newTopo.setDisponible(false);
		newTopo.setDateParution(topoForm.getDateParution());
		newTopo.setDescription(topoForm.getDescription());
		newTopo.setLieu(topoForm.getLieu());
		newTopo.setUtilisateur(utilisateur);
		newTopo.setSpot(spotx);
		newTopo.setSpotNom(topoForm.getSpotNom());
		
		topoRepository.save(newTopo);
		
	}

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
