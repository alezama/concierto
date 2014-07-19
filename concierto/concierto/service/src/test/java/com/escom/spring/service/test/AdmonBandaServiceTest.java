package com.escom.spring.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escom.spring.service.AdmonGeneroService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-service.xml","/context-repository.xml"})


public class AdmonBandaServiceTest {

	@Autowired
	AdmonGeneroService admonGeneroService;
	
	
	@Test
	public void testFindAll() {
		admonGeneroService.findAllGeneros();
		
	}
	
	
}
