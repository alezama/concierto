package com.escom.spring.service;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Compra;

public interface AdmonCompraService {
	
	abstract public Cliente addCliente(Cliente cliente);
	
	abstract public void deleteCompra(Compra compra);
}
