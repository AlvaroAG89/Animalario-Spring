package com.Animalario.springboot.datos.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Animalario.springboot.datos.app.models.entity.Jaula;

public interface IJaulaDao  extends CrudRepository<Jaula, String>{
	
	@Query("select p.jaula from Jaula p where p.laboratorio like :laboratorio")
	public List<String> findByLaboratorio(@Param("laboratorio") String laboratorio);
	
	@Query("select p.jaula from Jaula p where p.laboratorio not like :laboratorio")
	public List<String> findByOtherLaboratory(@Param("laboratorio") String laboratorio);

	@Query("select p.jaula from Jaula p")
	public List<String> findAllJaulas();
}
