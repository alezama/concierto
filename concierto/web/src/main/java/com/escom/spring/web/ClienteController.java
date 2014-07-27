package com.escom.spring.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.service.AdmonClienteService;
import com.escom.spring.service.AdmonConciertoService;


@Controller
public class ClienteController {
	
	@Autowired
	AdmonConciertoService admonConciertoService;
	
	@Autowired 
	AdmonClienteService admonClienteService;
	
	@ModelAttribute("conciertoList")
	public Concierto[] getGenero(){
		List<Concierto> generoAL = admonConciertoService.findAllConciertos();
		Concierto[] generoArr = new Concierto[generoAL.size()];
		return (Concierto[]) generoAL.toArray(generoArr);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/newCliente")
	public String requestNewConcierto(Map<String,Object> model){
		Cliente cliente = new Cliente();
		model.put("clienteForm", cliente);
		return "NewClienteForm";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/registerCliente")
	public String storeNewCompra(@ModelAttribute("clienteForm") Cliente cliente,
		  Map<String, Object> model) {
				
		admonClienteService.addCliente(cliente);
		model.put("objectId", cliente.getNombre());
		return "SuccessRegisterRecord";
	}
}
