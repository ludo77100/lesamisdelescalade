package com.ludo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ludo.dao.SiteEscaladeRepository;
import com.ludo.entities.SiteEscalade;

@Controller
public class SiteEscaladeController {
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;

	@GetMapping("/listeSiteEscalade")
	public String listeSiteEscalade(Model model) {
		List<SiteEscalade> siteEscalade = siteEscaladeRepository.findAll();
		model.addAttribute("listeSiteEscalade", siteEscalade);
		return "ListeSiteEscalade";
	}

	@GetMapping("/index")
	public String index() {
		return "index";

	}
}
