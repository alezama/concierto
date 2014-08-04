package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.service.exception.ServiceException;


public interface AdmonClienteService {

	public abstract void addClienteToConcierto(Concierto concierto, Cliente cliente, int numeroBoletos) throws ServiceException;

	public abstract Cliente getClienteById(Integer id);

	public abstract Cliente addCliente (Cliente cliente);
	
	public abstract List<Cliente> findAllClientes ();
		
	public abstract void deleteCliente (Cliente cliente);
}