package com.escom.spring.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escom.spring.entity.Cliente;
import com.escom.spring.repository.ClienteRepository;
import com.escom.spring.service.AdmonClienteService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-service.xml","/context-repository.xml"})

public class AdmonClientServiceTest {

	@Autowired
	AdmonClienteService admonClienteService;
	@Autowired
	ClienteRepository clienteRepository;
	
	
	@Before
	public void createTestClientes() {
		Cliente cliente1 = new Cliente();
		cliente1.setEdad(18);
		cliente1.setIdCliente(1);
		cliente1.setNombre("Anuar Lezama");
		
		clienteRepository.save(cliente1);
	}
	
	@After
	public void deleteTestClients() {
		if (clienteRepository.findOne(1) != null){
			clienteRepository.delete(1);
		}
	}
	
	@Test
	public void testFindCliente() {
		Cliente cliente = admonClienteService.getClienteById(1);
		Cliente result = clienteRepository.findOne(1);
		assertNotNull("El cliente retornado por el servicio es nulo", cliente);
		assertNotNull("El cliente retornado por el repositorio es nulo", result);
		assertEquals("Los clientes regresados no son los mismos", cliente.getIdCliente(), result.getIdCliente());
	}

}
