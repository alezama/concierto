package com.escom.spring.service;

import java.util.List;

import com.escom.spring.entity.Lugar;

public interface AdmonLugarService {

	public abstract void addLugar(Lugar lugar);
	public abstract List<Lugar> findAllLugares ();
	public abstract Lugar findLugarById (Integer id);
}