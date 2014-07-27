package com.escom.spring.web;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Compra;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonClienteService;
import com.escom.spring.service.AdmonCompraService;
import com.escom.spring.service.AdmonConciertoService;
import com.escom.spring.service.AdmonLugarService;

@Controller
public class CompraController {

	static Logger log = Logger.getLogger(CompraController.class.getName());


	@Autowired
	AdmonCompraService admonCompraService;
	@Autowired
	AdmonClienteService admonClienteService;
	@Autowired
	AdmonLugarService admonLugarService;
	@Autowired
	AdmonBandaService admonBandaService;
	@Autowired
	AdmonConciertoService admonConcieroService;

	@RequestMapping(method=RequestMethod.GET, value={"/newCompra"})
	public String requestNewConcierto(Map<String,Object> model){
		Compra compra = new Compra();
		model.put("formCompra", compra);
		model.put("clienteList", admonClienteService.findAllClientes());
		model.put("actionSubmit", "Buscar");
		return "NewCompraFormStart";
	}

	
	
	@RequestMapping(method=RequestMethod.POST, value="/processCompraFirstSearch") 
	public String processFirstSearch (Map<String, Object> model,
			@ModelAttribute("formCompra") Compra compra, 
			HttpServletRequest request ) {
		
		List<Concierto> conciertoList  = null;
		if (compra.getIdBanda() != null) {
			Banda banda = admonBandaService.findBandaById(compra.getIdBanda());
			conciertoList = admonConcieroService.getConciertosByBanda(banda);
			request.getSession().setAttribute("nombreBanda",banda.getNombre());
		}else if (compra.getIdLugar() != null) {
			Lugar lugar = admonLugarService.findLugarById(compra.getIdLugar());
			conciertoList = admonConcieroService.getConciertosByLugar(lugar);
			request.getSession().setAttribute("nombreLugar",lugar.getNombre());
		}
		if (conciertoList != null &&conciertoList.size() > 0 ){
			model.put("listaConciertos", 
					conciertoList);
			
		} else {
			model.put("errorMessage", "No hay conciertos para el criterio de búsqueda" );
			return "NewCompraFormFirstSearch";
		}
		
		return "NewCompraSecondSearch";
	}
		
	@RequestMapping(method=RequestMethod.POST, value="/processCompraFinish")
	public String processFinish (Map<String,Object> model, 
			@ModelAttribute("formCompra") Compra compra, 
			HttpServletRequest request){
		
		Concierto concierto = admonConcieroService.findById(compra.getIdConcierto());
		log.info("El valor del ID del cliente es: " +  request.getSession().getAttribute("idCliente"));
		Cliente cliente = admonClienteService.getClienteById((Integer) request.getSession().getAttribute("idCliente"));
		admonClienteService.addClienteToConcierto(concierto, cliente);


         model.put("objectId", compra.getNumeroBoletos() + " boletos han sido comprados para el concierto seleccionado");
         return "SuccessRegisterRecord";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/processCompraStart")
	public String processStart(Map<String, Object> model,
			@ModelAttribute("formCompra") Compra compra, 
			HttpServletRequest request) {
		
		Cliente cliente = admonClienteService.getClienteById(compra.getIdCliente());
		log.info("El valor del ID del cliente es: " +  cliente.getIdCliente());
		request.getSession().setAttribute("idCliente",cliente.getIdCliente());
		request.getSession().setAttribute("nombreCliente",cliente.getNombre());
		
		if(compra.getSeleccionCompra() == 0) {
			List<Lugar> lugarList = admonLugarService.findAllLugares();
			if(lugarList.size() > 0) {
				model.put("listLugares",lugarList);
				model.put("selectBanda", "");
			}else {
				model.put("errorMessage", "No se han dado de alta lugares para conciertos" );
				return  requestNewConcierto(model);

			}
		}
		//Request Search By Banda 
		else if (compra.getSeleccionCompra() == 1){
			List<Banda> bandaList = admonBandaService.findAllBandas();
			if (bandaList.size()>0) {
			model.put("listBandas",bandaList);
			model.put("selectBanda", "selected=selected");
			} else {
				model.put("errorMessage", "No se han dado de alta bandas para conciertos" );
				return  requestNewConcierto(model);
			}
		}
		
		return "NewCompraFormFirstSearch";
		
	}


}
