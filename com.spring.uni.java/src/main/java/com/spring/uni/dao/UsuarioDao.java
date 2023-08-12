package com.spring.uni.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.uni.domain.Usuario;

public interface UsuarioDao  extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);
}
