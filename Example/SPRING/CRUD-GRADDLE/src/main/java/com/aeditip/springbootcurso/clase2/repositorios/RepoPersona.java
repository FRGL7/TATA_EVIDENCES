package com.aeditip.springbootcurso.clase2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeditip.springbootcurso.clase2.entidades.Persona;

public interface RepoPersona extends JpaRepository<Persona,Integer> {
}
