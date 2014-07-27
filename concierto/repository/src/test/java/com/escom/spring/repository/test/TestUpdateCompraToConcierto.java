package com.escom.spring.repository.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.BandaRepository;
import com.escom.spring.repository.ClienteRepository;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.repository.GeneroRepository;
import com.escom.spring.repository.LugarRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-repository.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class TestUpdateCompraToConcierto {

	@Autowired
	ConciertoRepository conciertoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	BandaRepository bandaRepository;
	
	@Autowired
	LugarRepository lugarRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Test
	public void testCreation() {
		Cliente cliente = new Cliente();
		Concierto concierto = new Concierto();
		Banda banda = new Banda();
		Lugar lugar = new Lugar();
		Genero genero = new Genero();
		
		genero.setDescripcion("Hola la banda");
		
		genero = generoRepository.save(genero);
		
		
		banda.setGenero(genero);
		banda.setNombre("Los Pepes");
		banda.setRanking(2);
		
		banda = bandaRepository.save(banda);
		
		lugar.setCapacidad(1000);
		lugar.setDireccion("Los altos");
		lugar.setNombre("Parroquia");
		lugar.setRestriccionEdad(false);
		
		lugar = lugarRepository.save(lugar);
		
		concierto.setBanda(banda);
		concierto.setLugar(lugar);
		concierto.setFecha(new Date());
		
		cliente.setNombre("Pepe");
		cliente.setEdad(45);
		
		List<Cliente> clienteList = new ArrayList<Cliente>();
		List<Concierto> conciertoList = new ArrayList<Concierto>();
		
		clienteList.add(cliente);
		conciertoList.add(concierto);
		
		cliente.setConciertos(conciertoList);
		concierto.setClientes(clienteList);
		
		clienteRepository.save(cliente);
		conciertoRepository.save(concierto);
		
	}
	
	@Test
	@Transactional
	public void testClienteRep () {
				

		Cliente clienteRecovered = clienteRepository.findOne(0);
		Concierto concierto = conciertoRepository.findOne(1);
		
		assertNotNull(clienteRecovered);
		assertNotNull(concierto);
		
		concierto.getClientes().add(clienteRecovered);
		clienteRecovered.getConciertos().add(concierto);
		
		List<Cliente> listClients = concierto.getClientes();
		
		
		assertEquals(listClients.size(), 1);
		assertEquals(clienteRecovered.getConciertos().size(), 1);
		
	}
	
	@Transactional
	@Test
	public void testArrayAfterPersist() {
		
		Concierto concierto = conciertoRepository.findOne(1);
		List<Cliente> listClients = concierto.getClientes();
		assertEquals(1,listClients.size());
	}
	
}
