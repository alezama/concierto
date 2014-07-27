package com.escom.spring.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Genero;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonGeneroService;

@Controller
public class BandaController {

	@Autowired
	AdmonBandaService admonBandaService;
	
	@Autowired
	AdmonGeneroService admonGeneroService;
	
	@ModelAttribute("generoList")
	public Genero[] getGenero(){
		List<Genero> generoAL = admonGeneroService.findAllGeneros();
		Genero[] generoArr = new Genero[generoAL.size()];
		return (Genero[]) admonGeneroService.findAllGeneros().toArray(generoArr);
	}
	
	@ModelAttribute("bandaForm")
	public Banda createGenero() {
		return new Banda();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/newBanda")
	public String requestNewBanda (Map<String, Object> model) {
		model.put("bandaForm", new Banda());
		return "NewBandaForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/registroBandaResult")
	public String storeNewConcert(@ModelAttribute("bandaForm") Banda banda,
			@RequestParam("generoOption") Integer idGenero, Map<String, Object> model) {
		if (idGenero == null) {
			model.put("errorMessage", "Seleccione un genero válido");
			return requestNewBanda(model);
		}
		banda.setGenero(admonGeneroService.getGeneroById(idGenero));
		admonBandaService.addBanda(banda);
		model.put("objectId", banda.getNombre());
		return "SuccessRegisterRecord";
	}
}
