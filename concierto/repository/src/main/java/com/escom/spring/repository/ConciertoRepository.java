package com.escom.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.escom.spring.entity.Banda;
import com.escom.spring.entity.Concierto;
import com.escom.spring.entity.Lugar;

@Repository
public interface ConciertoRepository extends CrudRepository<Concierto, Integer> {

	@Query("SELECT c FROM Concierto c WHERE c.banda = :banda")
	public List<Concierto> findByBanda (@Param("banda") Banda banda);
	
	@Query("SELECT c FROM Concierto c WHERE c.lugar = :lugar")
	public List<Concierto> findByLugar (@Param("lugar") Lugar lugar);
	
	
}
