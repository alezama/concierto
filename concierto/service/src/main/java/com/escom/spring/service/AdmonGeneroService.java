package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Genero;

public interface AdmonGeneroService {

	public abstract Genero addGenero(Genero genero);
	public abstract List<Genero> findAllGeneros ();
	public abstract Genero getGeneroById (Integer id);
	public abstract void deleteGenero(Genero genero);
}