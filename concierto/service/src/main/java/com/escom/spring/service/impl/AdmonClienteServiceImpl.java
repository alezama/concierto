package com.escom.spring.service.impl;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.repository.ClienteRepository;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.service.AdmonClienteService;
import com.escom.spring.service.exception.ServiceException;

@Service
public class AdmonClienteServiceImpl implements AdmonClienteService {
	
	static Logger log = Logger.getLogger(AdmonClienteServiceImpl.class.getName());

	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ConciertoRepository conciertoRepository;
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonClienteService#addClienteToConcierto(com.escom.spring.entity.Concierto, com.escom.spring.entity.Cliente)
	 */
	
	@Transactional
	public void addClienteToConcierto(Concierto concierto, Cliente cliente, int numeroBoletos) throws ServiceException {
		
		if(concierto.getLugar() != null &&
				concierto.getLugar().getRestriccionEdad() &&
				cliente.getEdad() < 18){
			throw new ServiceException("El lugar tiene restricción de edad.");
		}
		
		if (validateTicketsBuy(concierto,cliente,numeroBoletos)) {
			
			for (int i = 0 ; i < numeroBoletos; i++) {
				concierto.addCliente(cliente);
				cliente.addConcierto(concierto);
			}
			conciertoRepository.save(concierto);
			
		}else {
			throw new ServiceException("El número de boletos a comprar es mayor al permitido.");
		}

	}
	
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonClienteService#getClienteById(java.lang.Integer)
	 */
	public Cliente getClienteById (Integer id) {
		Cliente fetchedClient = clienteRepository.findOne(id);
		fetchedClient.getConciertos();
		return fetchedClient;
	}
	
	public Cliente addCliente (Cliente cliente) {
		if (cliente.getConciertos()==null) {
			cliente.setConciertos(new ArrayList<Concierto>());
		}
		return clienteRepository.save(cliente);
	}

	

	@Override
	public List<Cliente> findAllClientes() {
		Iterable<Cliente> itG= clienteRepository.findAll();
		List<Cliente> returnList = new ArrayList<Cliente>();
		Iterator<Cliente> iter = itG.iterator();
		while (iter.hasNext()){
			returnList.add(iter.next());
		}
		return returnList;
	}
	


	/**
	 * Valida si el número de boletos que se van a comprar más los boletos que 
	 * ya han sido comprados por este mismo cliente es mayor a 5.
	 * @param concierto
	 * @param cliente
	 * @param numeroBoletos
	 * @return true si el numero de boletos ya comprados más el número de boletos
	 * por comprar es mayor a cinco, falso en caso de que sea menor o igual a 5.
	 */
	private boolean validateTicketsBuy(Concierto concierto, Cliente cliente, int numeroBoletos) {
		List<Cliente> listaClientes = concierto.getClientes();
		//Cuenta el total de boletos para ese concierto
		int counterTotalBoletos = 0;
		//Cuenta el total de boletos para ese cliente
		int maxCapcidad = concierto.getLugar().getCapacidad();
		int counter = 0;
		for (Cliente itCliente: listaClientes) {
			if (itCliente.getIdCliente() == cliente.getIdCliente()) {
				counter ++;
			}
			counterTotalBoletos ++;
		}
		log.info("El número de compras para ese cliente es: " + counter);
		log.info("El nuevo número de boletos a comprar son: " + numeroBoletos);
		log.info("Máxima capacidad del lugar: " + maxCapcidad);
		log.info("Total de boletos comprados para ese concierto: " + counterTotalBoletos);
		log.info("El resultado es " + (counter + numeroBoletos));
		return (counter + numeroBoletos <= 5) && (counterTotalBoletos + numeroBoletos <= maxCapcidad)  ;
	}


	@Override
	public void deleteCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}


	
}
