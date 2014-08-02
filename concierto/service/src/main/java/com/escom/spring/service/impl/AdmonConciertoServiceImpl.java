package com.escom.spring.service.impl;

import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.BandaRepository;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.service.AdmonConciertoService;


@Service
public class AdmonConciertoServiceImpl implements AdmonConciertoService  {

	@Autowired
	ConciertoRepository conciertoRepository;
	
	@Autowired
	BandaRepository bandaRepository;
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#getConciertosByBanda(com.escom.spring.entity.Banda)
	 */
	public List<Concierto> getConciertosByBanda(Banda banda) {
		return conciertoRepository.findByBanda(banda);
	}

	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#getConciertosByLugar(com.escom.spring.entity.Lugar)
	 */
	public List<Concierto> getConciertosByLugar(Lugar lugar) {
		return conciertoRepository.findByLugar(lugar);
	}
	
	public List<Concierto> findAllConciertos () {
		Iterable<Concierto> itG= conciertoRepository.findAll();
		List<Concierto> returnList = new ArrayList<Concierto>();
		Iterator<Concierto> iter = itG.iterator();
		while (iter.hasNext()){
			returnList.add(iter.next());
		}
		return returnList;
	}
	
	public Concierto findById (Integer id) {
		Concierto concierto = conciertoRepository.findOne(id);
		concierto.getClientes();
		return concierto;
	}
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#addConcierto(com.escom.spring.entity.Concierto)
	 */
	public void addConcierto (Concierto concierto) throws ServerException{
		
		concierto.getBanda();
		List<Concierto> listaConciertosFecha = conciertoRepository.findConciertosByDateAndBanda(concierto.getFecha(), concierto.getBanda());
		if(listaConciertosFecha.size() != 0) {
			throw new ServerException("La banda ya tiene un concierto para esa fecha");
		}
		if (concierto.getClientes()==null) {
			concierto.setClientes(new ArrayList<Cliente>());
		}
		conciertoRepository.save(concierto);
	}
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#deleteConcierto(com.escom.spring.entity.Concierto)
	 */
	public void deleteConcierto(Concierto concierto) {
		if (conciertoRepository.exists(concierto.getIdConcierto())) {
			conciertoRepository.delete(concierto.getIdConcierto());
		}
	}

	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#registerBanda(com.escom.spring.entity.Concierto, com.escom.spring.entity.Banda)
	 */
	public void registerBanda(Concierto concierto, Banda banda) {
		

	}
	
	public String printConcierto (Concierto concierto) {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
		sb.append(sdf.format(concierto.getFecha()));
		sb.append(" en el ");
		sb.append(concierto.getLugar());
		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#createConcierto(com.escom.spring.entity.Banda, com.escom.spring.entity.Lugar, java.util.Date)
	 */
	public void addParametersToConcierto(Banda banda, 
			Lugar lugar,
			Date fecha, Concierto concierto) throws ServerException {

		if (fecha == null){
			fecha = concierto.getFecha();
		}
		if (lugar == null) {
			lugar = concierto.getLugar();
		}
		if(banda == null) {
			banda = concierto.getBanda();
		}
		
		if (fecha == null || lugar==null || banda==null ){
			throw new ServerException("No hay suficientes parametros de entrada.");
		}
		
		//La banda no puede tener más de un concierto el mismo día
		List<Concierto> lugaresByFecha = conciertoRepository.findConciertosByDate(fecha);

		if (lugaresByFecha != null && lugaresByFecha.size() != 0){
			throw new ServerException("La banda ya tiene un concierto ese día.");
		}

		
		//La capacidad del lugar debe de corresponder con el ranking de la banda
		switch (banda.getRanking()) {
		case 1:
			if (lugar.getCapacidad() >100){
				concierto.setBanda(banda);
			} 
			break;
		case 2:
			if (lugar.getCapacidad() > 500){
				concierto.setBanda(banda);
			} 
			break;
		case 3:
			if (lugar.getCapacidad() > 2000){
				concierto.setBanda(banda);
			}
			break;
		default:
			throw new ServerException("El ranking de la banda no es válido.");
			
		}

		if (concierto.getBanda() == null) {
			throw new ServerException("El ranking de la banda no permite un concierto en este lugar por su capacidad.");

		}
		
	}

	



}
