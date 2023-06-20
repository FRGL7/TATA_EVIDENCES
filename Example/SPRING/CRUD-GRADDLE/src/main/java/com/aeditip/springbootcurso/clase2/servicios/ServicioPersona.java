package com.aeditip.springbootcurso.clase2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.persona.Cliente;
import com.aeditip.springbootcurso.clase2.entidades.Persona;
import com.aeditip.springbootcurso.clase2.repositorios.RepoCliente;
import com.aeditip.springbootcurso.clase2.repositorios.RepoPersona;

@Service
public class ServicioPersona {
    @Autowired
    private RepoPersona repo;
    @Autowired
    private RepoCliente repoCliente;

    /* Retrieve only one */
    public Persona obtenerCliente(int id){
        return repo.findById(id).orElse(null);
    }

    /* Retrieve all */
    public List<Cliente> obtenerCientes(){
        return repoCliente.findAll();
    }
}
