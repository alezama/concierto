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
	public void addClienteToConcierto(Concierto concierto, Cliente cliente) {
		
		concierto.addCliente(cliente);
		cliente.addConcierto(concierto);
		conciertoRepository.save(concierto);
	}
	
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonClienteService#getClienteById(java.lang.Integer)
	 */
	public Cliente getClienteById (Integer id) {
		Cliente fetchedClient = clienteRepository.findOne(id);
		fetchedClient.getConciertos();
		return fetchedClient;
	}
	
	public void addCliente (Cliente cliente) {
		if (cliente.getConciertos()==null) {
			cliente.setConciertos(new ArrayList<Concierto>());
		}
		clienteRepository.save(cliente);
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


	@Override
	public boolean validateTicketsBuy(Concierto concierto, Cliente cliente, int numeroBoletos) {
		List<Cliente> listaClientes = concierto.getClientes();
		int counter = 0;
		for (Cliente itCliente: listaClientes) {
			if (itCliente.getIdCliente() == cliente.getIdCliente()) {
				counter ++;
			}
		}
		log.info("El número de compras para ese cliente es: " + counter);
		log.info("El nuevo número de boletos a comprar son: " + numeroBoletos);
		log.info("El resultado es " + (counter + numeroBoletos));
		return counter + numeroBoletos > 5 ;
	}
}
