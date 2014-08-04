package com.escom.spring.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.escom.spring.service.exception.ServiceException;

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


		log.info("El IDLugar " + compra.getIdLugar());
		log.info("El IDBanda " + compra.getIdBanda());
		/*
		 * If the session is invalid return to the main newCompra page
		 */
		if (!isValidSession(request)){
			return requestNewConcierto(model);
		}

		/* Se borran los atributos de sesion que se pudieron setear durante un error
		 *  en el método processFinish (...) */
		request.getSession().removeAttribute("idLugar");
		request.getSession().removeAttribute("idBanda");

		List<Concierto> conciertoList  = null;
		if (compra.getIdBanda() != null) {
			Banda banda = admonBandaService.findBandaById(compra.getIdBanda());
			conciertoList = admonConcieroService.getConciertosByBanda(banda);
			request.getSession().setAttribute("idBanda", banda.getIdBanda());
			request.getSession().setAttribute("nombreBanda",banda.getNombre());
		}else if (compra.getIdLugar() != null) {
			Lugar lugar = admonLugarService.findLugarById(compra.getIdLugar());
			conciertoList = admonConcieroService.getConciertosByLugar(lugar);
			request.getSession().setAttribute("idLugar", lugar.getIdLugar());
			request.getSession().setAttribute("nombreLugar",lugar.getNombre());
		}
		// Filter the conciertoList, it should not show the conciertos which place
		// does not allow the underage in case it has this restriction, and the 
		// user is under 18 years.

		Boolean underAge = (Boolean) request.getSession().getAttribute("underAge");
		log.info("El cliente es menor de edad " + underAge.booleanValue());
		if (underAge.booleanValue()) {
			conciertoList = conciertoList.stream().filter( c -> !c.getLugar().getRestriccionEdad())
					.collect(Collectors.toList());
		}

		if (conciertoList != null &&conciertoList.size() > 0 ){
			model.put("listaConciertos", 
					conciertoList);

		} else {
			model.put("errorMessage", "No hay conciertos para el criterio de búsqueda" );
			compra.setIdCliente((Integer) request.getSession().getAttribute("idCliente"));
			compra.setSeleccionCompra((Integer) request.getSession().getAttribute("seleccionCompra"));

			return processStart( model, compra, request);
		}

		return "NewCompraSecondSearch";
	}

	@RequestMapping(method=RequestMethod.POST, value="/processCompraFinish")
	public String processFinish (Map<String,Object> model, 
			@ModelAttribute("formCompra") Compra compra, 
			HttpServletRequest request){

		/*
		 * If the session is invalid return to the main newCompra page
		 */
		if (!isValidSession(request)){
			return requestNewConcierto(model);
		}

		if (compra.getIdConcierto() == null ) {
			model.put("errorMessage", "Debes de seleccionar un concierto." );
			compra.setIdConcierto((Integer) request.getSession().getAttribute("idLugar"));
			compra.setIdBanda((Integer) request.getSession().getAttribute("idBanda"));
			return processFirstSearch (model, compra, request);
		}

		Concierto concierto = admonConcieroService.findById(compra.getIdConcierto());
		log.info("El valor del ID del cliente es: " +  request.getSession().getAttribute("idCliente"));
		Cliente cliente = admonClienteService.getClienteById((Integer) request.getSession().getAttribute("idCliente"));
		int numeroBoletos = compra.getNumeroBoletos();

		try {

			admonClienteService.addClienteToConcierto(concierto, cliente, numeroBoletos);

			model.put("objectId", compra.getNumeroBoletos() + " boletos han sido comprados para el concierto seleccionado");
			return "SuccessRegisterRecord";
		} catch (ServiceException e) {
			model.put("errorMessage", e.getMessage());
			log.info("El idDelLugar restablecido: " + (Integer) request.getSession().getAttribute("idLugar"));
			log.info("El idDeBanda restablecido: " + (Integer) request.getSession().getAttribute("idBanda"));
			compra.setIdLugar((Integer) request.getSession().getAttribute("idLugar"));
			compra.setIdBanda((Integer) request.getSession().getAttribute("idBanda"));
			return processFirstSearch (model, compra, request);
		}

}

@RequestMapping(method=RequestMethod.POST, value="/processCompraStart")
public String processStart(Map<String, Object> model,
		@ModelAttribute("formCompra") Compra compra, 
		HttpServletRequest request) {

	/*
	 * Limpio los atributos que se pudieron setear en un paso anterior
	 */
	request.getSession().removeAttribute("idBanda");
	request.getSession().removeAttribute("nombreBanda");
	request.getSession().removeAttribute("idLugar");
	request.getSession().removeAttribute("nombreLugar");
	request.getSession().removeAttribute("errorMessage");

	if (compra.getIdCliente() == null ) {
		model.put("errorMessage", "Selecciona un Cliente válido" );
		return  requestNewConcierto(model);
	}

	if (compra.getSeleccionCompra() == null) {
		model.put("errorMessage", "Seleccione un método de búsqueda válido." );
		return  requestNewConcierto(model);
	}

	Cliente cliente = admonClienteService.getClienteById(compra.getIdCliente());
	log.info("El valor del ID del cliente es: " +  cliente.getIdCliente());
	request.getSession().setAttribute("idCliente",cliente.getIdCliente());
	request.getSession().setAttribute("nombreCliente",cliente.getNombre());
	request.getSession().setAttribute("seleccionCompra",compra.getSeleccionCompra());
	request.getSession().setAttribute("underAge", cliente.getEdad()<18);

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

/**
 * The method validates if the session is still valid, to avoid the user to 
 * continue through the forms and avoid errors due to missing information
 * @param request
 * @return True if the session is valid, false otherwise. 
 */
public boolean isValidSession (HttpServletRequest request) {
	String nombreCliente = (String) request.getSession().getAttribute("nombreCliente");
	return 	nombreCliente != null && !nombreCliente.isEmpty();

}

}
