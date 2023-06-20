package com.aeditip.springbootcurso.clase2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeditip.springbootcurso.clase2.entidades.Pais;

public interface RepoPais extends JpaRepository<Pais, String>{
    
}
