package com.aeditip.springbootcurso.clase2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.Pais;
import com.aeditip.springbootcurso.clase2.repositorios.RepoPais;

@Service
public class ServicioPais {
    @Autowired
    private RepoPais repo;

    public Pais obtenerPais(String codigo){
        return repo.findById(codigo).orElse(null);
    }
}
