package com.Animalario.springboot.datos.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.Animalario.springboot.datos.app.models.service.JpaUserDetailsService;

//lo registramos como componente de spring
@Component
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler{

	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	

		
		
		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		
		FlashMap flashMap = new FlashMap();
		
		flashMap.put("success", "Hola " + authentication.getName() + ", has iniciado sesion con exito");
		
		//lo registramos en flashMapManager
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication != null){
			logger.info("El usuario '"+ authentication.getName() +"' ha iniciado sesión con éxito");
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
