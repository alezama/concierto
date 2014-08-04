package com.escom.spring.service.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonClienteService;
import com.escom.spring.service.AdmonConciertoService;
import com.escom.spring.service.AdmonGeneroService;
import com.escom.spring.service.AdmonLugarService;
import com.escom.spring.service.exception.ServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-service.xml","/context-repository.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class AdmonCompraServiceTest {




	@Autowired
	AdmonGeneroService admonGeneroService;
	@Autowired
	AdmonLugarService admonLugarService;
	@Autowired
	AdmonBandaService admonBandaService;
	@Autowired
	AdmonConciertoService admonConciertoService;
	@Autowired
	AdmonClienteService admonClienteService;

	Lugar lugarRest = null;
	Lugar lugarSinRest = null;
	Genero genero  = null;
	Banda banda = null;
	Concierto conciertoRest = null;
	Concierto conciertoSinRest = null;
	Cliente clienteMenor = null;
	Cliente clienteMayor = null;
	
	ArrayList<Cliente> clientesAL = new ArrayList<Cliente>();

	
	
	
	@Before
	@Transactional
	public void createObjects () throws ServiceException, ParseException {

		//Crear un genero
		genero = new Genero();
		genero.setDescripcion("Rock");
		genero = admonGeneroService.addGenero(genero);

		//Crear lugar con Restriccion de Edad
		lugarRest = new Lugar();
		lugarRest.setCapacidad(101);
		lugarRest.setNombre("Nombre lugar Rest");
		lugarRest.setDireccion("Direccion lugar Rest");
		lugarRest.setRestriccionEdad(true);
		lugarRest = admonLugarService.addLugar(lugarRest);


		//Crear lugar SIN Restriccion de Edad
		lugarSinRest = new Lugar();
		lugarSinRest.setCapacidad(101);
		lugarSinRest.setNombre("Nombre lugar SinRest");
		lugarSinRest.setDireccion("Direccion lugar SinRest");
		lugarSinRest.setRestriccionEdad(false);
		lugarSinRest = admonLugarService.addLugar(lugarSinRest);


		//Crear banda
		banda = new Banda();
		banda.setNombre("BandaR1");
		banda.setGenero(genero);
		banda.setRanking(1);
		banda = admonBandaService.addBanda(banda);


		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		//Crear conciertos
		conciertoRest = new Concierto();
		admonConciertoService.addParametersToConcierto(banda, lugarRest, sdf.parse("23/08/2015"), conciertoRest);
		admonConciertoService.addConcierto(conciertoRest);


		conciertoSinRest = new Concierto();
		admonConciertoService.addParametersToConcierto(banda, lugarSinRest, sdf.parse("24/08/2016"), conciertoSinRest);
		admonConciertoService.addConcierto(conciertoSinRest);


		//Crear clientes
		clienteMenor = new Cliente();
		clienteMenor.setEdad(13);
		clienteMenor.setNombre("Pepito");
		admonClienteService.addCliente(clienteMenor);

		clienteMayor = new Cliente();
		clienteMayor.setEdad(30);
		clienteMayor.setNombre("Pepe");
		admonClienteService.addCliente(clienteMayor);
		
		//Creamos 20 clientes
		for (int i = 0; i < 20 ; i ++){
			Cliente itClient = new Cliente();
			itClient.setNombre("cliente" + i);
			itClient.setEdad(20 + i);
			admonClienteService.addCliente(itClient);
			clientesAL.add(itClient);
		}

		
	}

	/*
	 * Hay ciertos lugares que no permiten la entrada a menores de edad, 
	 * así que el sistema debe verificar la edad de los clientes antes de
	 *  efectuar la compra pues no hay devoluciones
	 */

	@Test(expected=ServiceException.class)
	@Transactional
	public void compraMenorRest() throws ServiceException {


		admonClienteService.addClienteToConcierto(conciertoRest, clienteMenor, 1) ;

	}

	@Test
	@Transactional
	public void compraMayorRest() throws ServiceException {


		admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 1) ;
		assertEquals("No se compraron los boletos", 1, conciertoRest.getClientes().size() );
		conciertoRest.getClientes().clear();
		clienteMayor.getConciertos().clear();

	}

	@Test
	@Transactional
	public void compraMenorSinRest() throws ServiceException {


		admonClienteService.addClienteToConcierto(conciertoSinRest, clienteMenor, 1) ;
		assertEquals("No se compraron los boletos", 1, conciertoSinRest.getClientes().size() );
		conciertoSinRest.getClientes().clear();
		clienteMenor.getConciertos().clear();
	}

	@Test
	@Transactional
	public void compraMayorSinRest() throws ServiceException {


		admonClienteService.addClienteToConcierto(conciertoSinRest, clienteMayor, 1) ;
		assertEquals("No se compraron los boletos", 1, conciertoSinRest.getClientes().size() );
		conciertoSinRest.getClientes().clear();
		clienteMayor.getConciertos().clear();

	}

	/*
	 * Para evitar la reventa, un usuario no puede comprar más de 5 boletos
	 */

	@Test
	@Transactional
	public void compra5Boletos () throws ServiceException {
		admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 5);
		assertEquals("El número de boletos comprados no es 5", 5, conciertoRest.getClientes().size() );
		conciertoRest.getClientes().clear();
		clienteMayor.getConciertos().clear();
	}

	@Test(expected=ServiceException.class)
	@Transactional
	public void compra8Boletos () throws ServiceException {
		
				admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 8);
	}

	@Test(expected=ServiceException.class)
	@Transactional
	public void comprasIndependientes () throws ServiceException {
		admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 5);
		assertEquals("El número de boletos comprados no es 5", 5, conciertoRest.getClientes().size() );
		try {
			admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 1);
		}catch (ServiceException e) {
			throw e;
		}finally {
			conciertoRest.getClientes().clear();
			clienteMayor.getConciertos().clear();
		}

	}

	/*
	 *El sistema debe validar que no se vendan más boletos 
	 *de los establecidos por la capacidad del lugar 
	 */
	@Test
	@Transactional
	public void compra101Boletos () throws ServiceException {
		for (Cliente itClient: clientesAL) {
			admonClienteService.addClienteToConcierto(conciertoRest, itClient, 5);
		}
		admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 1);
		assertEquals("El número de boletos comprados no es 101", 101, conciertoRest.getClientes().size() );
		for (Cliente itClient: clientesAL) {
			itClient.getConciertos().clear();
		}
		clienteMayor.getConciertos().clear();
		conciertoRest.getClientes().clear();
	}
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void compra102Boletos() throws ServiceException {
		for (Cliente itClient: clientesAL) {
			admonClienteService.addClienteToConcierto(conciertoRest, itClient, 5);
		}
		try {
			admonClienteService.addClienteToConcierto(conciertoRest, clienteMayor, 2);
		} catch (ServiceException e) {
			throw e;
		} finally {
			for (Cliente itClient: clientesAL) {
				itClient.getConciertos().clear();
			}
			clienteMayor.getConciertos().clear();
			conciertoRest.getClientes().clear();
		}
	}


	@After
	@Transactional
	public void clearTestObjects() {
		
		
		if(conciertoRest !=null) {
			admonConciertoService.deleteConcierto(conciertoRest);
		}
		
		if(conciertoSinRest !=null) {
			admonConciertoService.deleteConcierto(conciertoSinRest);
		}
		
		if(clienteMayor != null) {
			admonClienteService.deleteCliente(clienteMayor);
		}
		
		if(clienteMenor != null) {
			admonClienteService.deleteCliente(clienteMenor);
		}
		
		for (Cliente itCliente : clientesAL) {
			if (itCliente != null) {
				admonClienteService.deleteCliente(itCliente);
			}
		}
		
		if (lugarRest != null) {
			admonLugarService.deleteLugar(lugarRest);
		}
		
		if (lugarSinRest != null) {
			admonLugarService.deleteLugar(lugarSinRest);
		}
		
		if(banda != null) {
			admonBandaService.deleteBanda(banda);
		}
		
		if(genero != null) {
			admonGeneroService.deleteGenero(genero);
		}
	}


}
