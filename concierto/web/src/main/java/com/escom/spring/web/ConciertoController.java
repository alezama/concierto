package com.escom.spring.web;

import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonConciertoService;
import com.escom.spring.service.AdmonLugarService;
import com.escom.spring.service.exception.ServiceException;

@Controller
public class ConciertoController {
	
	@Autowired
	AdmonConciertoService admonConciertoService;
	@Autowired
	AdmonBandaService admonBandaService;
	@Autowired
	AdmonLugarService admonLugarService;
	
	
	@ModelAttribute("bandaList")
	public List<Banda> getListBanda () {
		return  admonBandaService.findAllBandas();
	}
	
	@ModelAttribute("lugarList")
	public List<Lugar> getListLugar () {
		 return admonLugarService.findAllLugares();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/newConcierto")
	public String requestNewConcierto(Map<String,Object> model){
		Concierto concierto = new Concierto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.put("todaysDate", sdf.format(new Date()));
		model.put("conciertoForm", concierto);
		return "NewConciertoForm";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/registroConciertoResult")
	public String storeNewConcert(@ModelAttribute("conciertoForm") Concierto concierto,
			@RequestParam("lugarOption") Integer lugarID,
			@RequestParam("bandaOption") Integer bandaID,
			Map<String, Object> model) {
		
		if (lugarID == null || bandaID == null) {
			if (lugarID == null) {
				model.put("errorMessage", "Se debe de seleccionar un lugar válido.");
			} else if (bandaID == null) {
				model.put("errorMessage", "Se debe de seleccionar una banda válida.");
			}
			return requestNewConcierto(model);
		}
		
		try {
		admonConciertoService.addParametersToConcierto(
				admonBandaService.findBandaById(bandaID), 
				admonLugarService.findLugarById(lugarID),
				null,
				concierto);
		admonConciertoService.addConcierto(concierto);
		} catch (ServiceException e) {
			model.put("errorMessage", e.getMessage());
			return requestNewConcierto(model);
		}
		model.put("objectId", concierto.getBanda().getNombre());
		return "SuccessRegisterRecord";
	}
}
