package com.ludo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ludo.entities.Topo;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

}
