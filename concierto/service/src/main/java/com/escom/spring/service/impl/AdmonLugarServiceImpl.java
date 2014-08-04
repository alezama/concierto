package com.escom.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Lugar;
import com.escom.spring.repository.LugarRepository;
import com.escom.spring.service.AdmonLugarService;

@Service
public class AdmonLugarServiceImpl implements AdmonLugarService {

	@Autowired
	LugarRepository lugarRepository;
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonLugarService#addLugar(com.escom.spring.entity.Lugar)
	 */
	public Lugar addLugar(Lugar lugar) {
		
		return lugarRepository.save(lugar);
	}
	
	public List<Lugar> findAllLugares () {
		Iterable<Lugar> itG= lugarRepository.findAll();
		List<Lugar> returnList = new ArrayList<Lugar>();
		Iterator<Lugar> iter = itG.iterator();
		while (iter.hasNext()){
			returnList.add(iter.next());
		}
		
		return returnList;
	}
	
	public Lugar findLugarById (Integer id) {
		return lugarRepository.findOne(id);
	}

	@Override
	public void deleteLugar(Lugar lugar) {
		lugarRepository.delete(lugar);
	}
	
}
