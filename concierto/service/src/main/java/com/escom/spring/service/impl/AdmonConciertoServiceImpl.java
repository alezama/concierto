package com.escom.spring.service.impl;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.service.AdmonConciertoService;


@Service
public class AdmonConciertoServiceImpl implements AdmonConciertoService  {

	@Autowired
	ConciertoRepository conciertoRepository;
	
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
	public void addConcierto (Concierto concierto){
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

	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonConciertoService#createConcierto(com.escom.spring.entity.Banda, com.escom.spring.entity.Lugar, java.util.Date)
	 */
	public Concierto createConcierto(Banda banda, Lugar lugar, Date fecha) throws ServerException {
		Concierto concierto = new Concierto();
		
		//La banda no puede tener más de un concierto el mismo día
		for (Concierto conciertoIt: banda.getConciertos()){
			if (conciertoIt.getFecha().equals(fecha)){
				throw new ServerException("La banda ya tiene un concierto ese día.");
			}
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
			throw new ServerException("El ranking de la banda no corresponde con la capacidad del lugar.");

		}
		return concierto;
	}

	



}
