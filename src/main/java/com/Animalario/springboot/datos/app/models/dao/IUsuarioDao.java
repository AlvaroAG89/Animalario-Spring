package com.Animalario.springboot.datos.app.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Animalario.springboot.datos.app.models.entity.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	@Query("select p from Usuario p where p.usuario like ?1 and p.contraseña like ?2")
	public Usuario findUserAndPass( String user, String pass);
	
	@Query("select p from Usuario p where p.usuario like ?1")
	public Usuario findByUsuario(String user);


}
