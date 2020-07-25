package com.Animalario.springboot.datos.app.models.service;

import java.util.List;

import com.Animalario.springboot.datos.app.models.entity.Aviso;
import com.Animalario.springboot.datos.app.models.entity.Jaula;
import com.Animalario.springboot.datos.app.models.entity.Rack;
import com.Animalario.springboot.datos.app.models.entity.Usuario;


public interface IReservasService {
	
	//Manejo de Jaulas
	public List<Jaula> findAllJaula();
	
	public void saveJaula(Jaula jaula);
	
	public Jaula findOneJaula(String id);
	
	public void deleteJaula(String id);
	
	public List<String> busquedaOcupados(String laboratorio);
	
	public List<String> busquedaPropios(Usuario miUsuario);
	
	public String crearJSON(List<String> racks, String nombreArchivo);
	
	public String leerJSON(String nombreArchivo);
	
	
	
	//Manejo de Racks
	
	public List<Rack> findAllRack();
	
	public void saveRack (Rack rack);
	
	public Rack findOneRack(String id);
	
	public void deleteRack(String id);
	
	public List<String> findListRack();
	
	
	//Manejo de Usuarios
	
	public List<Usuario> findAllUsers();
	
	public void saveUser (Usuario user);
	
	public Usuario findOneUser(String username);
	
	public void deleteUser(String id);
	
	public Usuario ComprobarLoging(String usuario, String contraseña);
	
	
	//Manejo de Avisos
//	public List<Aviso> findAllAviso();
//	
//	
//	public void saveAviso (Aviso aviso);
//	
//	public Aviso findOneAviso(int id);
//	
//	public void deleteAviso(int id);


	
}
