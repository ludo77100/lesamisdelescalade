package com.ludo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.ludo.entities.Topo;

public interface TopoService {

	Optional<Topo> findById(Long topoId);

	void saveTopo(Topo topo);

	List<Topo> findAllByUser(UserDetails utilDet);

	void deleteById(Long topoId);

	void changerDispoTopo(Long topoId);

}
