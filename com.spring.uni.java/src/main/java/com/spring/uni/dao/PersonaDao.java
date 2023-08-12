package com.spring.uni.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.uni.domain.Persona;

public interface PersonaDao extends CrudRepository<Persona, Long> {

}
