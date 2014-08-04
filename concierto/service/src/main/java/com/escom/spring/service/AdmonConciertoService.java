package com.escom.spring.service;

import java.rmi.ServerException;
import java.util.Date;
import java.util.List;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;
import com.escom.spring.service.exception.ServiceException;

public interface AdmonConciertoService {

	public abstract List<Concierto> getConciertosByBanda(Banda banda);

	public abstract List<Concierto> getConciertosByLugar(Lugar lugar);

	public abstract void addConcierto(Concierto concierto) throws ServiceException;

	public abstract void deleteConcierto(Concierto concierto);

	public abstract void registerBanda(Concierto concierto, Banda banda);

	public abstract void addParametersToConcierto(Banda banda, 
			Lugar lugar,
			Date fecha, Concierto concierto) throws ServiceException;

	public abstract  List<Concierto> findAllConciertos ();
	
	public abstract Concierto findById (Integer id);
	
	public abstract String printConcierto (Concierto concierto);
}