package com.escom.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escom.spring.entity.Banda;
import com.escom.spring.repository.BandaRepository;
import com.escom.spring.service.AdmonBandaService;

@Service
public class AdmonBandaServiceImpl implements AdmonBandaService{

	@Autowired
	BandaRepository bandaRepository;
	
	public List<Banda> findAllBandas () {
		Iterable<Banda> itG= bandaRepository.findAll();
		List<Banda> returnList = new ArrayList<Banda>();
		Iterator<Banda> iter = itG.iterator();
		while (iter.hasNext()){
			returnList.add(iter.next());
		}
		
		return returnList;
	}
	
	
	public Banda addBanda (Banda banda) {
		return bandaRepository.save(banda);
	}
	
	public Banda findBandaById (Integer id) {
		return bandaRepository.findOne(id);
	}


	@Override
	public void deleteBanda(Banda banda) {
		Banda deleted = bandaRepository.findOne(banda.getIdBanda());
		if (deleted != null) {
			bandaRepository.delete(deleted);
		}
	}
}
