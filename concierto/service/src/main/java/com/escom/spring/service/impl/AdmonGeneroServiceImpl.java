package com.escom.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Genero;
import com.escom.spring.repository.GeneroRepository;
import com.escom.spring.service.AdmonGeneroService;

@Service
public class AdmonGeneroServiceImpl implements AdmonGeneroService {

	@Autowired
	GeneroRepository generoRepository;
	
	/* (non-Javadoc)
	 * @see com.escom.spring.service.impl.AdmonGeneroService#addGenero(com.escom.spring.entity.Genero)
	 */
	public void addGenero (Genero genero) {
		generoRepository.save(genero);
	}
	
	public List<Genero> findAllGeneros () {
		Iterable<Genero> itG= generoRepository.findAll();
		List<Genero> returnList = new ArrayList<Genero>();
		Iterator<Genero> iter = itG.iterator();
		while (iter.hasNext()){
			returnList.add(iter.next());
		}
		
		return returnList;
	}
	
}
