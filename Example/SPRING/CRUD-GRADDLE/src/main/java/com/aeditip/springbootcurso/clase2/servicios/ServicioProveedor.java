package com.aeditip.springbootcurso.clase2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.Pais;
import com.aeditip.springbootcurso.clase2.entidades.Proveedor;
import com.aeditip.springbootcurso.clase2.repositorios.RepoPais;
import com.aeditip.springbootcurso.clase2.repositorios.RepoProveedor;

@Service
public class ServicioProveedor {
    @Autowired
    private RepoProveedor repo;
    @Autowired
    private RepoPais repoPais;

    /* Create */
    public Proveedor crearProveedor(Proveedor proveedor, String codPais){
        // Buscar el país
        Pais auxPais = repoPais.findById(codPais).orElse(null);
        if(auxPais != null){
            proveedor.setPais(auxPais);
            proveedor.setActivo(1);
            return repo.save(proveedor);
        }
        System.out.println("El país " + codPais + " no existe en la BD.");
        return null;
    }
    
    /* Retrieve all */
    public List<Proveedor> obtenerProveedores(){
        return repo.findAll();
    }

    public Proveedor obtenerProveedor(int idProveedor){
        return repo.findById(idProveedor).orElse(null);
    }
}
