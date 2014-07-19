package com.escom.spring.service;

import java.rmi.ServerException;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;


public interface AdmonClienteService {

	public abstract void addClienteToConcierto(Concierto concierto,
			Cliente cliente) throws ServerException;

	public abstract Cliente getClienteById(Integer id);

}