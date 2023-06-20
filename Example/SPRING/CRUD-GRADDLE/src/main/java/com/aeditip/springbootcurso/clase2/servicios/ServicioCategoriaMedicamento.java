package com.aeditip.springbootcurso.clase2.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.medicamento.CategoriaMedicamento;
import com.aeditip.springbootcurso.clase2.repositorios.RepoCategoriaMedicamento;

@Service
public class ServicioCategoriaMedicamento {
    @Autowired
    private RepoCategoriaMedicamento repo;

    public CategoriaMedicamento obtenerCategoria(int idCategoria){
        return repo.findById(idCategoria).orElse(null);
    }

    public CategoriaMedicamento obtenerCategoriaPorNombre(String nombre){
        return repo.findByNombre(nombre).orElse(null);
    }
}
