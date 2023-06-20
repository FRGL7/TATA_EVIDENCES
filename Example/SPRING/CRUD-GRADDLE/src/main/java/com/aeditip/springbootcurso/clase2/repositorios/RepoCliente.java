package com.aeditip.springbootcurso.clase2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeditip.springbootcurso.clase2.entidades.persona.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, Integer> {
    
}
