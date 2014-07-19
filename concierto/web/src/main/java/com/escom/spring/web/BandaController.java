package com.escom.spring.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(method=RequestMethod.GET, value="/newBanda")
	public String requestNewBanda (Map<String, Object> model) {
		List<Genero> generosList = admonGeneroService.findAllGeneros();
		Map<Integer, String> generosHM = new LinkedHashMap<Integer, String>();
		for (Genero genero: generosList) {
			generosHM.put(genero.getIdGenero(), genero.getDescripcion());
		}
		Banda banda = new Banda();
		model.put("generoList", generosHM);
		model.put("bandaForm", banda);
		return "NewBandaForm";
	}
}
