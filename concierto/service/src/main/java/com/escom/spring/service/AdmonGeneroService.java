package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Genero;

public interface AdmonGeneroService {

	public abstract void addGenero(Genero genero);
	public abstract List<Genero> findAllGeneros ();
	
}