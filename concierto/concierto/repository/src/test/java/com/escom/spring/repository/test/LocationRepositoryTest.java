package com.escom.spring.repository.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.BandaRepository;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.repository.GeneroRepository;
import com.escom.spring.repository.LugarRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-repository.xml"})
public class LocationRepositoryTest {

	@Autowired
	ConciertoRepository conciertoRepository;
	
	@Autowired
	BandaRepository bandaRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Autowired
	LugarRepository lugarRepository;
	
	@Before
	public void cargaDatos () {
		
		Lugar lugar1 = new Lugar();
		lugar1.setCapacidad(2500);
		lugar1.setIdLugar(1);
		lugar1.setDireccion("Av Circuito Interior");
		lugar1.setNombre("Palacio de los Recortes");
		lugar1.setRestriccionEdad(false);
		
		lugarRepository.save(lugar1);
		
		Lugar lugar2 = new Lugar();
		lugar2.setCapacidad(250);
		lugar2.setIdLugar(2);
		lugar2.setDireccion("Av de los Maestros");
		lugar2.setNombre("Auditorio Mario Colin");
		lugar2.setRestriccionEdad(true);
		
		lugarRepository.save(lugar2);
		
		Genero genero1 = new Genero();
		genero1.setIdGenero(1);
		genero1.setDescripcion("Rock, Populachero");
				
		Banda banda = new Banda();
		banda.setIdBanda(1);
		banda.setNombre("Los Tucanes de Tijuana");
		banda.setRanking(3);
		banda.setGenero(genero1);
		
		bandaRepository.save(banda);
	}
	
	
	@After 
	public void deleteDatos() {
		
		if(bandaRepository.findOne(1) != null) {
			bandaRepository.delete(1);
		}
		
		if (generoRepository.findOne(1) != null) {
			generoRepository.delete(1);
		}
		
		if(lugarRepository.findOne(1) != null){
			lugarRepository.delete(1);
		}
		
		if(lugarRepository.findOne(2) != null){
			lugarRepository.delete(2);
		}
		
		
	}
	
	
	@Test
	public void testLugarRepository() {
		
		Banda retrievedBanda = bandaRepository.findOne(1);
		assertNotNull("La banda traida es nula", retrievedBanda);
	}
	
	@Test
	public void testBooleanLugar() {
		Lugar lugarFalse = lugarRepository.findOne(1);
		Lugar lugarTrue = lugarRepository.findOne(2);
		
		assertFalse("El lugar no tiene una capacidad de false", lugarFalse.getRestriccionEdad());
		assertTrue("El lugar no tiene una capacidad de true", lugarTrue.getRestriccionEdad());
	}

}
