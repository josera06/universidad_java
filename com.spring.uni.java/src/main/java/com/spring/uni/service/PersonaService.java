package com.spring.uni.service;

import java.util.List;

import com.spring.uni.domain.Persona;

public interface PersonaService {
	
	public List<Persona> listarPersonas();
	
	public void guardar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrar(Persona persona);
}
