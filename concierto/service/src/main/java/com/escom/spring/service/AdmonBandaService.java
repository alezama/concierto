package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Banda;

public interface AdmonBandaService {
	
	
	public abstract  List<Banda> findAllBandas ();
	
	public abstract Banda addBanda (Banda banda);
	
	public abstract Banda findBandaById (Integer id);
	
	public abstract void deleteBanda (Banda banda);
}
	