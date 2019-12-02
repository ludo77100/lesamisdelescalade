package com.ludo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.dao.SecteurRepository;
import com.ludo.dao.SpotRepository;
import com.ludo.entities.Secteur;
import com.ludo.entities.Spot;
import com.ludo.forms.SpotForm;
import com.ludo.metier.SpotService;

@Controller
public class SpotController {
	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private SecteurRepository secteurRepository ;
	@Autowired
	private SpotService spotService ;
	
	
	
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
		Spot spot = spotRepository.findById(spotId).get();
		model.addAttribute("spotInfo", spot);
		/*
		 * Optional<Spot> spotInfo = spotRepository.findById(spotId);
		 * model.addAttribute("spotInfo", spotInfo.get());
		 */
		return "spot";
	}
	@GetMapping("/ajouter")
	public String formSpot(Model model) {
		model.addAttribute("spot", new Spot());
		return "formSpot" ;
	}
	
	@PostMapping("/save")
	public String saveSpot(Model model, @ModelAttribute("spotForm") SpotForm spotForm) {
		spotService.saveSpot(spotForm);
		return "confAjoutSpot" ;
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";

	}
}
