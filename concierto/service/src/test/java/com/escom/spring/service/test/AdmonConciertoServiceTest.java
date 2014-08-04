package com.escom.spring.service.test;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Genero;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.AdmonBandaService;
import com.escom.spring.service.AdmonConciertoService;
import com.escom.spring.service.AdmonGeneroService;
import com.escom.spring.service.AdmonLugarService;
import com.escom.spring.service.exception.ServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/context-service.xml","/context-repository.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class AdmonConciertoServiceTest {

	@Autowired
	AdmonGeneroService admonGeneroService;
	@Autowired
	AdmonLugarService admonLugarService;
	@Autowired
	AdmonBandaService admonBandaService;
	@Autowired
	AdmonConciertoService admonConciertoService;


	Genero genero  = null;

	Lugar lugar100 = null;
	Lugar lugar101 = null;
	Lugar lugar501 = null;
	Lugar lugar2001 = null;

	Banda bandaR1 = null;
	Banda bandaR2 = null;
	Banda bandaR3 = null;
	
	Concierto concierto1 = null;
	Concierto concierto2 = null;

	@Before
	@Transactional
	public void createCompraSet() {
		//Crear un genero
		genero = new Genero();
		genero.setDescripcion("Rock");
		genero = admonGeneroService.addGenero(genero);

		//Crear lugares
		lugar100 = new Lugar();
		lugar100.setCapacidad(100);
		lugar100.setNombre("Nombre lugar 100");
		lugar100.setDireccion("Direccion lugar 100");
		lugar100.setRestriccionEdad(false);
		lugar100 = admonLugarService.addLugar(lugar100);

		lugar101 = new Lugar();
		lugar101.setCapacidad(101);
		lugar101.setNombre("Nombre lugar 101");
		lugar101.setRestriccionEdad(false);
		lugar101.setDireccion("Direccion lugar 101");
		lugar101 = admonLugarService.addLugar(lugar101);

		lugar501 = new Lugar();
		lugar501.setCapacidad(501);
		lugar501.setNombre("Nombre lugar 501");
		lugar501.setDireccion("Direccion lugar 501");
		lugar501.setRestriccionEdad(false);
		lugar501 = admonLugarService.addLugar(lugar501);

		lugar2001 = new Lugar();
		lugar2001.setCapacidad(2001);
		lugar2001.setNombre("Nombre lugar 2001");
		lugar2001.setRestriccionEdad(false);
		lugar2001.setDireccion("Direccion lugar 2001");
		lugar2001 = admonLugarService.addLugar(lugar2001);


		//Crear Banda

		bandaR1 = new Banda();
		bandaR1.setNombre("BandaR1");
		bandaR1.setGenero(genero);
		bandaR1.setRanking(1);
		bandaR1 = admonBandaService.addBanda(bandaR1);

		bandaR2 = new Banda();
		bandaR2.setNombre("BandaR2");
		bandaR2.setGenero(genero);
		bandaR2.setRanking(2);
		bandaR2 = admonBandaService.addBanda(bandaR2);

		bandaR3 = new Banda();
		bandaR3.setNombre("BandaR3");
		bandaR3.setGenero(genero);
		bandaR3.setRanking(3);
		bandaR3 = admonBandaService.addBanda(bandaR3);


	}



	@After
	@Transactional
	public void deleteTestObjects (){
		
		if(concierto1 != null) {
			admonConciertoService.deleteConcierto(concierto1);
		}

		if(concierto2 != null) {
			admonConciertoService.deleteConcierto(concierto2);
		}
		
		if(bandaR1 != null) {
			admonBandaService.deleteBanda(bandaR1);
		}

		if(bandaR2 != null) {
			admonBandaService.deleteBanda(bandaR2);
		}

		if(bandaR3 != null) {
			admonBandaService.deleteBanda(bandaR3);
		}

		if (genero != null) {
			admonGeneroService.deleteGenero(genero);
		}

		if (lugar100 != null) {
			admonLugarService.deleteLugar(lugar100);
		}

		if (lugar101 != null) {
			admonLugarService.deleteLugar(lugar101);
		}

		if (lugar501 != null) {
			admonLugarService.deleteLugar(lugar501);
		}

		if (lugar2001 != null) {
			admonLugarService.deleteLugar(lugar2001);
		}


	}

	@Test
	@Transactional
	public void verifiedSavedItems() {
		Genero generoR = admonGeneroService.getGeneroById(genero.getIdGenero());
		assertNotNull("El genero no se guardó", generoR);
		assertEquals("El nombre del genero no es correcto.", generoR.getDescripcion(),"Rock");

		Lugar lugar100R = admonLugarService.findLugarById(lugar100.getIdLugar());
		assertNotNull("El lugar "+ lugar100.getNombre() +" no se guardo", lugar100R);
		assertEquals("El nombre del lugar " +lugar100.getNombre() + " no se guardó correctamente", lugar100R.getNombre(),"Nombre lugar 100");
		assertEquals("La dirección del lugar "+lugar100.getNombre()+" no se guardó correctamente",
				lugar100R.getDireccion(), "Direccion lugar 100");
		assertEquals("La capacidad del lugar "+lugar100.getNombre() +" no se guardó correctamente", 
				lugar100R.getCapacidad(),100);

		Lugar lugar101R = admonLugarService.findLugarById(lugar101.getIdLugar());
		assertNotNull("El lugar  "+ lugar101.getNombre() +" no se guardo", lugar101R);
		assertEquals("El nombre del lugar " +lugar101.getNombre() + " no se guardó correctamente", lugar101R.getNombre(),"Nombre lugar 101");
		assertEquals("La dirección del lugar "+lugar101.getNombre()+" no se guardó correctamente",
				lugar101R.getDireccion(), "Direccion lugar 101");
		assertEquals("La capacidad del lugar "+lugar101.getNombre() +" no se guardó correctamente",
				lugar101R.getCapacidad(),101);

		Lugar lugar501R = admonLugarService.findLugarById(lugar501.getIdLugar());
		assertNotNull("El lugar  "+ lugar501.getNombre() +" no se guardo", lugar501R);
		assertEquals("El nombre del lugar " +lugar501.getNombre() + " no se guardó correctamente", lugar501R.getNombre(),"Nombre lugar 501");
		assertEquals("La dirección del lugar "+lugar501.getNombre()+" no se guardó correctamente",
				lugar501R.getDireccion(), "Direccion lugar 501");
		assertEquals("La capacidad del lugar "+lugar501.getNombre() +" no se guardó correctamente", 
				lugar501R.getCapacidad(),501);

		Lugar lugar2001R = admonLugarService.findLugarById(lugar2001.getIdLugar());
		assertEquals("El nombre del lugar " +lugar2001.getNombre() + " no se guardó correctamente", lugar2001R.getNombre(),"Nombre lugar 2001");
		assertNotNull("El lugar id "+ lugar2001.getNombre() +" no se guardo", lugar2001R);
		assertEquals("La dirección del lugar "+lugar2001.getNombre()+" no se guardó correctamente",
				lugar2001R.getDireccion(), "Direccion lugar 2001");
		assertEquals("La capacidad del lugar "+lugar2001.getNombre() +" no se guardó correctamente", 
				lugar2001R.getCapacidad(),2001);


		Banda bandaR1R = admonBandaService.findBandaById(bandaR1.getIdBanda());
		assertNotNull("La banda -"+bandaR1.getIdBanda()+" no se guardó", bandaR1R);
		assertEquals("El nombre de la banda - "+bandaR1.getNombre()+" no se guardó correctamente", bandaR1R.getNombre(),"BandaR1");
		assertEquals("El ranking de la banda - "+bandaR1.getNombre()+" no se guardó correctamente", bandaR1R.getRanking(),1);
		assertEquals("El genero de la banda - "+bandaR1.getNombre()+" no se guardó correctamente", bandaR1R.getGenero().getIdGenero(), genero.getIdGenero());

		Banda bandaR2R = admonBandaService.findBandaById(bandaR2.getIdBanda());
		assertNotNull("La banda  -"+bandaR2.getNombre()+" no se guardó", bandaR2R);
		assertEquals("El nombre de la banda  - "+bandaR2.getNombre()+" no se guardó correctamente", bandaR2R.getNombre(),"BandaR2");
		assertEquals("El ranking de la banda  - "+bandaR2.getNombre()+" no se guardó correctamente", bandaR2R.getRanking(),2);
		assertEquals("El genero de la banda - "+bandaR2.getNombre()+" no se guardó correctamente", bandaR2R.getGenero().getIdGenero(), genero.getIdGenero());

		Banda bandaR3R = admonBandaService.findBandaById(bandaR3.getIdBanda());
		assertNotNull("La banda  -"+bandaR3.getNombre()+" no se guardó", bandaR3R);
		assertEquals("El nombre de la banda  - "+bandaR3.getNombre()+" no se guardó correctamente", bandaR3R.getNombre(),"BandaR3");
		assertEquals("El ranking de la banda - "+bandaR3.getNombre()+" no se guardó correctamente", bandaR3R.getRanking(),3);
		assertEquals("El genero de la banda  - "+bandaR3.getNombre()+" no se guardó correctamente", bandaR3R.getGenero().getIdGenero(), genero.getIdGenero());

	}


	/*
	 * Es importante considerar que no todas las bandas se presentan en todos los lugares, considere la siguiente tabla:
	   Ranking de la banda	Capacidad
	   1 estrella	Más de 100 personas
	   2 estrellas	Más de 500 personas
	   3 estrellas	Más de 2000 personas
	 */
	
	@Test
	@Transactional
	public void recordNewConciertoR1 () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR1, lugar101, new Date(), concierto);
	}

	@Test
	@Transactional
	public void recordNewConciertoR2 () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR2, lugar501, new Date(), concierto);
	}

	@Test
	@Transactional
	public void recordNewConciertoR3 () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR3, lugar2001, new Date(), concierto);
	}

	@Test(expected=ServiceException.class)
	@Transactional
	public void recordNewConciertoR1Exc () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR1, lugar100, new Date(), concierto);
	}

	@Test(expected=ServiceException.class)
	@Transactional
	public void recordNewConciertoR2Exc () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR2, lugar101, new Date(), concierto);
	}

	@Test(expected=ServiceException.class)
	@Transactional
	public void recordNewConciertoR3Exc () throws ServiceException {
		Concierto concierto = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR3, lugar501, new Date(), concierto);
	}
	
	/* 
	 * Una banda no puede tener más de un concierto el mismo día.
	 */
	
	@Test(expected=ServiceException.class)
	@Transactional
	public void recordNewConciertoSameDateExc () throws ServiceException {
		concierto1 = new Concierto ();
		concierto2 = new Concierto ();
		admonConciertoService.addParametersToConcierto(bandaR1, lugar101, new Date(), concierto1);
		admonConciertoService.addConcierto(concierto1);
		admonConciertoService.addParametersToConcierto(bandaR1, lugar501, new Date(), concierto2);
		admonConciertoService.addConcierto(concierto2);
	}
	
	
}
