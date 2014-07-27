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
	
	/**
	 * Valida si el número de boletos que se van a comprar más los boletos que 
	 * ya han sido comprados por este mismo cliente es mayor a 5.
	 * @param concierto
	 * @param cliente
	 * @param numeroBoletos
	 * @return true si el numero de boletos ya comprados más el número de boletos
	 * por comprar es mayor a cinco, falso en caso de que sea menor o igual a 5.
	 */
	public abstract boolean validateTicketsBuy(Concierto concierto, Cliente cliente, int numeroBoletos);
}