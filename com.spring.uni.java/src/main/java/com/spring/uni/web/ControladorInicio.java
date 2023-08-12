package com.spring.uni.web;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.uni.dao.PersonaDao;
import com.spring.uni.domain.Persona;
import com.spring.uni.service.PersonaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

	Logger logger = LoggerFactory.getLogger(ControladorInicio.class);
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public String inicio(Model model, @AuthenticationPrincipal User user) {
		logger.info("usuario login: " + user);
		var personas = personaService.listarPersonas();
		model.addAttribute("personas", personas);

		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		
		return "modificar";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {
		if(errores.hasErrors()) {
			return "modificar";
		}
		personaService.guardar(persona);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona,Model model) {
		persona = personaService.encontrar(persona);
		model.addAttribute("persona", persona);
		return "modificar";
	}
	
	@GetMapping("/eliminar/{idPersona}")
	public String eliminar(Persona persona,Model model) {
		personaService.eliminar(persona);
		return "redirect:/";
	}
}
