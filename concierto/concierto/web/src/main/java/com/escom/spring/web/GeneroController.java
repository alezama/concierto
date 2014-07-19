package com.escom.spring.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.escom.spring.entity.Genero;
import com.escom.spring.service.AdmonGeneroService;

@Controller
public class GeneroController {

	@Autowired
	AdmonGeneroService admonGeneroService;
	
	@RequestMapping(method=RequestMethod.GET, value="/newGenero")
	public String requestNewGenero(Map<String,Object> model){
		Genero genero = new Genero();
		model.put("generoForm", genero);
		return "NewGeneroForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/registroGeneroResult")
	public String processAddGenero (@ModelAttribute("generoForm") Genero genero, Map<String, Object> model){
		admonGeneroService.addGenero(genero);
		model.put("objectId", genero.getDescripcion());
		return "SuccessRegisterRecord";
	}
	
}
