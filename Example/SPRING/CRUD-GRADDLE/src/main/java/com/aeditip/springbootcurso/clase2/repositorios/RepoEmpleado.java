package com.aeditip.springbootcurso.clase2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeditip.springbootcurso.clase2.entidades.persona.Empleado;

public interface RepoEmpleado extends JpaRepository<Empleado, Integer> {
    
}
