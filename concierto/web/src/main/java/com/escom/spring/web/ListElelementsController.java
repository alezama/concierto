package com.escom.spring.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonClienteService;
import com.escom.spring.service.AdmonConciertoService;
import com.escom.spring.service.AdmonGeneroService;
import com.escom.spring.service.AdmonLugarService;


@Controller
public class ListElelementsController {

	@Autowired
	AdmonGeneroService admonGeneroService;
	@Autowired
	AdmonLugarService admonLugarService;
	@Autowired
	AdmonBandaService admonBandaService;
	@Autowired
	AdmonClienteService admonClienteService;
	@Autowired
	AdmonConciertoService admonConciertoService;
	
	static Logger log = Logger.getLogger(ListElelementsController.class.getName());

	
	@RequestMapping(method=RequestMethod.GET, value="/listGeneros")
	public String listGeneros(Map<String, Object> model){
		List<Genero> listaGeneros = admonGeneroService.findAllGeneros();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Descripción");
		
		List<List<String>> dataList = new ArrayList<List<String>>();
		for (Genero genero: listaGeneros) {
			List<String> itList = new ArrayList<String>();
			itList.add(genero.getDescripcion());
			dataList.add(itList);
		}
		
		model.put("tableHeaders", headerList);
		model.put("dataList", dataList);
		model.put("tableTitle", "Lista de Generos");

		return "ListElements";
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listLugares")
	public String listLugares(Map<String, Object> model){
		List<Lugar> listaLugares = admonLugarService.findAllLugares();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Nombre");
		headerList.add("Dirección");
		headerList.add("Capacidad");
		headerList.add("Restricción de Edad");
		
		List<List<String>> dataList = new ArrayList<List<String>>();
		for(Lugar lugar: listaLugares) {
			List<String> itList = new ArrayList<String>();
			itList.add(lugar.getNombre());
			itList.add(lugar.getDireccion());
			itList.add(Integer.toString(lugar.getCapacidad()));
			itList.add(lugar.getRestriccionEdad()?"Sí":"No");
			dataList.add(itList);
		}
		
		model.put("tableHeaders", headerList);
		model.put("dataList", dataList);
		model.put("tableTitle", "Lista de Lugares");

		return "ListElements";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listBandas")
	public String listBandas(Map<String, Object> model){
		List<Banda> listaBandas = admonBandaService.findAllBandas();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Nombre");
		headerList.add("Genero");
		headerList.add("Ranking");
		headerList.add("Número de Conciertos");
		
		List<List<String>> dataList = new ArrayList<List<String>>();
		for(Banda banda: listaBandas) {
			log.info("Iterando la banda " + banda.getNombre());
			List<String> itList = new ArrayList<String>();
			itList.add(banda.getNombre());
			itList.add(banda.getGenero().getDescripcion());
			itList.add(Integer.toString(banda.getRanking()));
			itList.add(Integer.toString(admonConciertoService.getConciertosByBanda(banda).size()));
			dataList.add(itList);
		}
		
		model.put("tableHeaders", headerList);
		model.put("dataList", dataList);
		model.put("tableTitle", "Lista de Bandas");
		return "ListElements";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listClientes")
	public String listClientes(Map<String, Object> model){
		List<Cliente> listaClientes = admonClienteService.findAllClientes();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Nombre");
		headerList.add("Edad");
	
		List<List<String>> dataList = new ArrayList<List<String>>();
		for(Cliente cliente: listaClientes) {
			List<String> itList = new ArrayList<String>();
			itList.add(cliente.getNombre());
			itList.add(Integer.toString(cliente.getEdad()));
			dataList.add(itList);
		}
		
		model.put("tableHeaders", headerList);
		model.put("dataList", dataList);
		model.put("tableTitle", "Lista de Clientes");

		return "ListElements";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listConciertos")
	public String listConciertos(Map<String, Object> model){
		List<Concierto> listaConcierto = admonConciertoService.findAllConciertos();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Banda");
		headerList.add("Fecha");
		headerList.add("Lugar");
		headerList.add("Boletos Vendidos");
		headerList.add("Boletos Disponibles");
	
		List<List<String>> dataList = new ArrayList<List<String>>();
		for(Concierto concierto: listaConcierto) {
			List<String> itList = new ArrayList<String>();
			itList.add(concierto.getBanda().getNombre());
			SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy");
			itList.add(sdf.format(concierto.getFecha()));
			itList.add(concierto.getLugar().getNombre());
			itList.add(Integer.toString(concierto.getClientes().size()));
			itList.add(Integer.toString(concierto.getLugar().getCapacidad()- concierto.getClientes().size()));
			dataList.add(itList);
		}
		
		model.put("tableHeaders", headerList);
		model.put("dataList", dataList);
		model.put("tableTitle", "Lista de Conciertos");

		
		return "ListElements";
	}
}
