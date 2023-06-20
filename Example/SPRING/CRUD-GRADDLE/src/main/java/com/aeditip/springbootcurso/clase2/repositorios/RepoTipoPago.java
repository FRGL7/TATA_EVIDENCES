package com.aeditip.springbootcurso.clase2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeditip.springbootcurso.clase2.entidades.TipoPago;

public interface RepoTipoPago extends JpaRepository<TipoPago, Integer>{
    
}
