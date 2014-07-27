package com.escom.spring.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonLugarService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-service.xml","/context-repository.xml"})
public class AdmonLugarServiceTest {

	@Autowired
	AdmonLugarService admonLugarService;
	
	
	@Test
	public void testFindAllLugares () {
		List<Lugar> lugarList = admonLugarService.findAllLugares();
		
		for (Lugar lugar: lugarList){
			System.out.println(lugar.getNombre());
		}
		
	}
}
