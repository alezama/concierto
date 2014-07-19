package com.escom.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escom.spring.entity.Lugar;

@Repository
public interface LugarRepository extends CrudRepository<Lugar, Integer> {

}
