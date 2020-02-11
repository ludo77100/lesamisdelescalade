package com.ludo.service;

import java.util.Optional;

import javax.validation.Valid;

import com.ludo.entities.Spot;

public interface SpotService {

	Optional<Spot> findById(Long spotId);

	void saveSpot(@Valid Spot spot);

	void saveEditSpot(@Valid Spot spot, Long spotId);

	void rendreOfficiel(Long spotId);

	void deleteById(Long spotId);

}
