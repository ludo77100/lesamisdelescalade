package com.ludo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ludo.dao.SiteEscaladeRepository;
import com.ludo.entities.SiteEscalade;

@Controller
public class SiteEscaladeController {
	@Autowired
	private SiteEscaladeRepository siteEscaladeRepository;

	@GetMapping("/listeSiteEscalade")
	public String listeSiteEscalade(Model model, 
			@RequestParam(name="page", defaultValue = "0")int p, 
			@RequestParam(name="size", defaultValue = "4")int s,
			@RequestParam(name="mc", defaultValue = "")String mc) {
		Page<SiteEscalade> pageSiteEscalade = 
				siteEscaladeRepository.chercher("%"+mc+"%", PageRequest.of(p, s));
		model.addAttribute("listeSiteEscalade", pageSiteEscalade.getContent());
		int[] pages = new int[pageSiteEscalade.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		return "ListeSiteEscalade";
	}

	@GetMapping("/index")
	public String index() {
		return "index";

	}
}
