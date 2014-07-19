package com.escom.spring.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonLugarService;


@Controller
public class LugarController {
	
	@Autowired
	AdmonLugarService admonLugarService;
	
	@RequestMapping(method=RequestMethod.GET, value="/newLugar")
	public String requestNewLugar (Map<String, Object> model){
		Lugar lugarForm = new Lugar();
		model.put("lugarForm", lugarForm);
		return "NewLugarForm";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/registroLugarResult")
	public String processAddLugar(@ModelAttribute("lugarForm") Lugar lugar, Map<String, Object> model){
		admonLugarService.addLugar(lugar);
		model.put("objectId", lugar.getNombre());
		return "SuccessRegisterRecord";
		
	}
	
	
}
