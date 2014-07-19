package com.escom.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escom.spring.entity.Banda;

@Repository
public interface BandaRepository extends CrudRepository<Banda, Integer> {

}
