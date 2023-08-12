package com.spring.uni.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.uni.dao.PersonaDao;
import com.spring.uni.domain.Persona;

@Service
public class PersonaServiceImp implements PersonaService {

	@Autowired
	private PersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		return (List<Persona>) personaDao.findAll(); 
	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personaDao.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrar(Persona persona) {
		return personaDao.findById(persona.getIdPersona()).orElse(null);
	}

}
