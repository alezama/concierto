package com.escom.spring.repository.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.ConciertoRepository;
import com.escom.spring.repository.LugarRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-repository.xml"})

public class TestQueries {

	
	@Autowired
	LugarRepository lugarRepository;
	
	@Autowired
	ConciertoRepository conciertoRepository;
	
	@Test
	public void test() {
		Lugar lugar = lugarRepository.findOne(3);
		List<Concierto> conciertos =  conciertoRepository.findByLugar(lugar);
		assertNotNull(conciertos);
		assertEquals(conciertos.size(), 1);
	}

}
