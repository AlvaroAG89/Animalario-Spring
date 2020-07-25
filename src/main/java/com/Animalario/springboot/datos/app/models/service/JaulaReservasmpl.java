package com.Animalario.springboot.datos.app.models.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animalario.springboot.datos.app.controllers.JaulaController;
import com.Animalario.springboot.datos.app.models.dao.IAvisoDao;
import com.Animalario.springboot.datos.app.models.dao.IJaulaDao;
import com.Animalario.springboot.datos.app.models.dao.IRackDao;
import com.Animalario.springboot.datos.app.models.dao.IUsuarioDao;
import com.Animalario.springboot.datos.app.models.entity.Aviso;
import com.Animalario.springboot.datos.app.models.entity.Jaula;
import com.Animalario.springboot.datos.app.models.entity.Rack;
import com.Animalario.springboot.datos.app.models.entity.Usuario;
import com.google.gson.Gson;


@Service
public class JaulaReservasmpl implements IReservasService {
	
	@Autowired
	private IJaulaDao jaulaDao;
	
	@Autowired
	private IRackDao rackDao;
	
	@Autowired
	private IUsuarioDao userDao;
	
//	@Autowired
//	private IAvisoDao avisoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Jaula> findAllJaula() {
		return (List<Jaula>) jaulaDao.findAll();
	}

	@Transactional
	@Override
	public void saveJaula(Jaula jaula) {
		jaulaDao.save(jaula);
	}

	@Transactional(readOnly=true)
	@Override
	public Jaula findOneJaula(String id) {
		return jaulaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteJaula(String id) {
		jaulaDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<String> busquedaOcupados(String laboratorio) {
		return jaulaDao.findByOtherLaboratory(laboratorio);
	}

	@Override
	@Transactional
	public List<String> busquedaPropios(Usuario miUsuario) {
		
		if(JaulaController.hasRole("ROLE_ADMIN") == true) {
			return jaulaDao.findAllJaulas();
		}else{
			return jaulaDao.findByLaboratorio(miUsuario.getLaboratorio());
		}
		
		
	}
	
	@Override
	public String crearJSON(List<String> racks, String nombreArchivo) {
		Gson gson = new Gson();
		String JSON = gson.toJson(racks);

		return JSON;
	}
	
	
	@Override
	public String leerJSON(String nombreArchivo) {
		String direccion = new File (nombreArchivo + ".json").getAbsolutePath();
		String cadena = null;
		//Cargamos la ruta absoluta en el fichero
		File f = new File (direccion);
		
		Scanner entf = null;
		try {
			entf = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(entf.hasNextLine()) {
			cadena = entf.next();
		}

		entf.close();
		
		return cadena;
	}
	
	
	
	
	//-----------------------MANEJO DE RACKS----------------------//

	@Override
	@Transactional(readOnly=true)
	public List<Rack> findAllRack() {
		return (List<Rack>) rackDao.findAll();
	}

	@Override
	@Transactional
	public void saveRack(Rack rack) {
		rackDao.save(rack);
	}

	@Override
	@Transactional(readOnly=true)
	public Rack findOneRack(String id) {
		return rackDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteRack(String id) {
		rackDao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<String> findListRack(){
		List<String> listaRack = rackDao.listaRacks();
		return listaRack;
	}

	
	//-----------------------MANEJO DE USUARIOS-------------------//
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAllUsers() {
		return (List<Usuario>) userDao.findAll();
	}

	@Override
	@Transactional
	public void saveUser(Usuario user) {
		userDao.save(user);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOneUser(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsuario(username);
	}
	

	@Override
	@Transactional
	public Usuario ComprobarLoging(String usuario, String contraseña) {
		// TODO Auto-generated method stub
		return userDao.findUserAndPass(usuario, contraseña);
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		Usuario usuario = userDao.findByUsuario(username);
		userDao.deleteById(usuario.getId());
	}


	
	//----------------------MANEJO DE AVISOS---------------------//
//	
//	@Override
//	@Transactional(readOnly=true)
//	public List<Aviso> findAllAviso() {
//		return (List<Aviso>) avisoDao.findAll();
//	}
//
//	@Override
//	@Transactional
//	public void saveAviso(Aviso aviso) {
//		avisoDao.save(aviso);
//	}
//
//	@Override
//	@Transactional(readOnly=true)
//	public Aviso findOneAviso(int id) {
//		return avisoDao.findById(id).orElse(null);
//	}
//
//	@Override
//	@Transactional
//	public void deleteAviso(int id) {
//		avisoDao.deleteById(id);
//	}


	
}
