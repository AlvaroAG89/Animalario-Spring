package com.Animalario.springboot.datos.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Animalario.springboot.datos.app.models.dao.IUsuarioDao;
import com.Animalario.springboot.datos.app.models.entity.Role;
import com.Animalario.springboot.datos.app.models.entity.Usuario;


@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("El username es '" + username + "'");
		Usuario usuario = usuarioDao.findByUsuario(username);
		
		if(usuario == null) {
			logger.error("Error login: no existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("Username " + username + " no existe en el sistema");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		int contador = 0;
		for(Role role: usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			logger.info("Su rol es '" + authorities.get(contador) + "'");
			contador++;
		}
		
		if(authorities.isEmpty()) {
			logger.error("Error login: usuario '" + username + "' no tiene roles asignados!");
			throw new UsernameNotFoundException("Error login: usuario '" + username + "' no tiene roles asignados!");
		}
		
		//cargamos una instancia de SpringSecurity con los datos que luego en
		//SpringSecurity seran necesarios para la validacion
		return new User(usuario.getUsuario(), usuario.getContraseña(), usuario.getEnabled(), true, true, true, authorities);
	}

}
