package com.escom.spring.service;

import java.rmi.ServerException;
import java.util.List;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Compra;
import com.escom.spring.entity.Concierto;


public interface AdmonClienteService {

	public abstract void addClienteToConcierto(Concierto concierto, Cliente cliente);

	public abstract Cliente getClienteById(Integer id);

	public abstract void addCliente (Cliente cliente);
	
	public abstract List<Cliente> findAllClientes ();
}