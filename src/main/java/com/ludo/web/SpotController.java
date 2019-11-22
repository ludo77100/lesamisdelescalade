package com.ludo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.dao.SpotRepository;
import com.ludo.entities.Spot;

@Controller
public class SpotController {
	@Autowired
	private SpotRepository spotRepository;
	@GetMapping("/listeSpot")
	public String listeSpot(Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "4")int s,
			@RequestParam(name="mc", defaultValue = "")String mc) {
		
		Page<Spot> pageListeSpot = 
				spotRepository.chercher("%"+mc+"%", PageRequest.of(p, s));
		model.addAttribute("listeSpot", pageListeSpot.getContent());
		int[] pages = new int[pageListeSpot.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		return "listeSpot";
		
	}

	@GetMapping("/spot/{spotId}")
	public String spot(Model model, @PathVariable("spotId") Long spotId) {
		Optional<Spot> spotInfo = spotRepository.findById(spotId);
		model.addAttribute("spotInfo", spotInfo.get());
		return "spot";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";

	}
}
