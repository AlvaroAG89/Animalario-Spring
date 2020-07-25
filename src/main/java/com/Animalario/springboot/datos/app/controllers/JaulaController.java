package com.Animalario.springboot.datos.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Animalario.springboot.datos.app.models.entity.Jaula;
import com.Animalario.springboot.datos.app.models.entity.Rack;
import com.Animalario.springboot.datos.app.models.entity.Role;
import com.Animalario.springboot.datos.app.models.entity.Usuario;
import com.Animalario.springboot.datos.app.models.service.IReservasService;
import com.Animalario.springboot.datos.app.models.service.JpaUserDetailsService;


@Controller
@RequestMapping("/Jaulas")
@SessionAttributes("usuario")
public class JaulaController {
		
	@Autowired
	private IReservasService jaulaService;
	
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	//private Usuario usuario;
	

	//Con SessionAtributes recupero la sesion que cree en LoginController
	//Y con @ModelAttribute asigno esa sesion a una variable para mantenerla
	

	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = {"/estante/{tipo}"}, method = RequestMethod.GET)
	public String estante(@PathVariable(value = "tipo") String tipo, Model model, 
			Authentication authentication,/*@ModelAttribute("usuario") Usuario usuario,*/ HttpServletRequest request) {

		//------------------------PROVISIONAL-----------------------//		
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Usuario miUsuario = jaulaService.findOneUser(auth.getName());
		logger.info(miUsuario.getUsuario() + ", " + miUsuario.getId());
		HttpSession session = request.getSession();
		session.setAttribute("usuario", miUsuario);
		
		Usuario usuario = miUsuario;
		
		//si quito esto peta en la union lazy
		logger.info("Dato " + usuario.getRoles().get(0));

		//----------------------------------------------------------//
		

			
		if(authentication != null) {
			logger.info("Hola usuario autentificado, tu username es: ".concat(authentication.getName()));
		}
		
		List<String> rackPropios = jaulaService.busquedaPropios(usuario);

		List<String> rackOcupados = null;
		
		if(hasRole("ROLE_ADMIN") == false) {
			rackOcupados = jaulaService.busquedaOcupados(usuario.getLaboratorio());
		}
		

		
		for(int i = 0; i<rackPropios.size(); i++) {
			logger.info("Rack '" + rackPropios.get(i) + "'");
		}
		

		String ocupados  = jaulaService.crearJSON(rackOcupados, "racksOcupados");
		String propios = jaulaService.crearJSON(rackPropios, "racksLibres");
		
		
		
		model.addAttribute("titulo", "Rack " + tipo);
		model.addAttribute("tipo", tipo);
		model.addAttribute("rackOcupados", ocupados);
		model.addAttribute("rackPropios", propios);
		model.addAttribute("usuario", usuario);
		return "estante";
	}
	
	
	// Con esto enviamos el objeto que luego pediremos recoger para guardar
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value="/añadir/formulario/{rack}")
	public String formulario(@PathVariable(value = "rack") String id, @ModelAttribute("usuario") Usuario usuario, Map<String, Object> model, RedirectAttributes flash) {
		//Funcion que vuelva a comprobar que esta libre pendiente
		
		Jaula jaula;
		jaula = jaulaService.findOneJaula(id);
		String role = myRole(usuario);
		
		//Cargamos el seleccionador de Rack
		List<String> listaRack = jaulaService.findListRack();
		
		if(jaula!= null) {
			//editamos
			if(!id.equals("")) {
				jaula = jaulaService.findOneJaula(id);
				if(jaula.getLaboratorio().equals(usuario.getLaboratorio()) || hasRole("ROLE_ADMIN")==true) {
				model.put("jaula", jaula);
				model.put("titulo", "Editar Jaula");
				model.put("role", role);
				model.put("listaRack", listaRack);
				
				}else {
					flash.addFlashAttribute("error", "Esa Jaula esta vacia o no te pertenece");
					return "redirect:/Jaulas/estante/A";
				}
			}else {
				flash.addFlashAttribute("error", "El ID del cliente no puede ser nulo");
				return "redirect:/Jaulas/estante/A";
			}
			
			
		}else {
			//Lo metemos nuevo
			
			jaula = new Jaula();
			jaula.setJaula(id);
			jaula.setLaboratorio(usuario.getLaboratorio());
			jaula.setResponsable(usuario.getNombre());
			model.put("jaula", jaula);
			model.put("role", role);
			model.put("usuario", usuario);
			model.put("titulo", "Añadir Rack a la Jaula");
			model.put("listaRack", listaRack);
		}

		return "formulario";
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value="/añadir/formulario")	
	public String crear (Jaula jaula, @ModelAttribute("usuario") Usuario usuario,
			 RedirectAttributes flash, Map<String, Object> model) {
		
			model.put("usuario", usuario);
			model.put("titulo", "Añadir Rack a la Jaula");
						
			return "formulario";
	}
	
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value="/añadir/formulario", method=RequestMethod.POST)	
	public String guardar(@Valid Jaula jaula, BindingResult result, @ModelAttribute("usuario") Usuario usuario,
			 RedirectAttributes flash, Model model) {

		Rack miRack = jaulaService.findOneRack(jaula.getTipo_jaula());
		Jaula oldJaula = jaulaService.findOneJaula(jaula.getJaula());
		
		//Si la jaula existe, y hemos seleccionado otra jaula hay que comprobar la nueva
		if(oldJaula!=null) {
			if(oldJaula.getTipo_jaula()!= jaula.getTipo_jaula()) {
				
				Rack oldRack = jaulaService.findOneRack(oldJaula.getTipo_jaula());
				
				//Comprobamos que tenemos Stock, si no tenemos generamos error
				if(miRack.getStock() == 0) {
					result.rejectValue("tipo_jaula", "tipo_jaula", "No hay mas stock de rack " + miRack.getTipo_caja());
				}
				
				//si lo tenemos actualizamos el rack antiguo y nuevo
				else {
					
					oldRack.setStock(oldRack.getStock()+1);
					jaulaService.saveRack(oldRack);
					//la nueva se actualiza despues con la jaula
				}
			}
			
			//Comprobamos si el Administrador cambia la ubicación de la caja
			if(oldJaula.getJaula()!= jaula.getJaula()) {
				Jaula comprobacion = jaulaService.findOneJaula(jaula.getJaula());
				if(comprobacion != null) {
					result.rejectValue("jaula", "jaula", "Esa jaula esta ocupada");
				}
			}
		}else { //Si es un rack nuevo hay que comprobarlo tambien
			//Comprobamos que tenemos Stock, si no tenemos generamos error
			if(miRack.getStock() == 0) {
			
				result.rejectValue("tipo_jaula", "tipo_jaula", "No hay mas stock de rack " + miRack.getTipo_caja());

			}
			
		}
		

		if(result.hasErrors()) {
			//Cargamos el seleccionador de Rack
			List<String> listaRack = jaulaService.findListRack();
			
			System.out.println(result.toString());
			String role = myRole(usuario);
			System.out.println("Hay un error");
			model.addAttribute("titulo", "Editar Jaula");
			model.addAttribute("usuario", usuario);
			model.addAttribute("role", role);
			model.addAttribute("listaRack", listaRack);
			
			return "formulario";
		}
		
		//Actualizamos el stock del rack
		miRack.setStock(miRack.getStock()-1);
		jaulaService.saveRack(miRack);
		
		//Insertamos la jaula
		String mensajeFlash = (jaula.getFecha_reserva()!=null ? "Jaula editada con éxito!" : "Jaula Creada con éxito!");
		jaulaService.saveJaula(jaula);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/Jaulas/estante/A";
	}
	
	
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value="/eliminar/{codigo}")
	public String eliminar(@PathVariable(value="codigo") String codigo, @ModelAttribute("usuario") Usuario usuario, Model model) {
		Jaula jaula = jaulaService.findOneJaula(codigo);
		logger.info(jaula.toString());
		if(jaula.getLaboratorio().equals(usuario.getLaboratorio()) || hasRole("ROLE_ADMIN")==true) {
			jaulaService.deleteJaula(codigo);
		}
		
		return "redirect:/Jaulas/estante/A";
	}	
	
	
	public static boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		//esta coleccion lista cualquier tipo de objeto que herede de GrantedAuthority o la implemente
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities(); 
		
		//Si contiene el role pasado, devuelve un true
		return authorities.contains(new SimpleGrantedAuthority(role));
	}
	
	
	public static String myRole(Usuario usuario) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role: usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return authorities.get(0).toString();
	}

}
