package com.escom.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escom.spring.entity.Genero;

@Repository
public interface GeneroRepository extends CrudRepository<Genero, Integer> {

}
