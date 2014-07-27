package com.escom.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Cliente;
import com.escom.spring.repository.ClienteRepository;
import com.escom.spring.service.AdmonCompraService;

@Service
public class AdmonCompraServiceImpl implements AdmonCompraService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public void addCliente(Cliente cliente){
		clienteRepository.save(cliente);
	}
}
