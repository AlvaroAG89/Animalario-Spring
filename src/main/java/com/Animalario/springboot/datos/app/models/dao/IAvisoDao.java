package com.Animalario.springboot.datos.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Animalario.springboot.datos.app.models.entity.Aviso;

@Repository
public interface IAvisoDao extends JpaRepository<Aviso, Integer> {

}
