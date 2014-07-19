package com.escom.spring.service.impl;

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
	public void addLugar(Lugar lugar) {
		
		lugarRepository.save(lugar);
	}
	
	
}
