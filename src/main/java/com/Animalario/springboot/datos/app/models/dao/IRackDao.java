package com.Animalario.springboot.datos.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Animalario.springboot.datos.app.models.entity.Rack;

public interface IRackDao extends CrudRepository<Rack, String>{

	@Query("select r.tipo_caja from Rack r")
	public List<String> listaRacks ();
}
