package com.ludo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ludo.entities.SiteEscalade;

@Controller
public class SiteEscaladeController {
@Autowired
	private SiteEscalade siteEscalade ;
	@RequestMapping(value="/siteEscalade")
	public String index() {
		return "SiteEscalade" ;
	}
	
	
}