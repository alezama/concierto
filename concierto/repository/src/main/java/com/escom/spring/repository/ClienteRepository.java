package com.escom.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.escom.spring.entity.Cliente;
import com.escom.spring.entity.Concierto;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {


	
}
