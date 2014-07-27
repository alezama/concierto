package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Banda;

public interface AdmonBandaService {
	
	
	public abstract  List<Banda> findAllBandas ();
	
	public abstract void addBanda (Banda banda);
	
	public abstract Banda findBandaById (Integer id);
}
	