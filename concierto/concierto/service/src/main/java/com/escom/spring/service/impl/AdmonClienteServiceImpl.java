package com.escom.spring.service.impl;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.repository.ClienteRepository;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.service.AdmonClienteService;

@Service
public class AdmonClienteServiceImpl implements AdmonClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonClienteService#addClienteToConcierto(com.escom.spring.entity.Concierto, com.escom.spring.entity.Cliente)
	 */
	public void addClienteToConcierto(Concierto concierto, Cliente cliente)
			throws ServerException {
		
		//El cliente registrado en el lugar no puede ser mayor que la capacidad del lugar
		if (concierto.getClientes().size() + 1 > concierto.getLugar().getCapacidad()){
			throw new ServerException("La máxima capacidad del lugar ha sido alcanzada.");
		}
		
		concierto.getClientes().add(cliente);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonClienteService#getClienteById(java.lang.Integer)
	 */
	public Cliente getClienteById (Integer id) {
		return clienteRepository.findOne(id);
	}
	
	
}
