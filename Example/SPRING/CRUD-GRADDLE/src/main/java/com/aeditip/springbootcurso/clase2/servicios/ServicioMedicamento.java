package com.aeditip.springbootcurso.clase2.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.Medicamento;
import com.aeditip.springbootcurso.clase2.repositorios.RepoMedicamento;

@Service
public class ServicioMedicamento {
    @Autowired
    private RepoMedicamento repo;

    /* CRUD: Create, retrieve, update & delete */

    /* Create */
    public Medicamento crearMedicamento(Medicamento medicamento){
        medicamento.setActivo(1);
        return repo.save(medicamento);
    }

    /* Retrieve all */
    public List<Medicamento> obtenerMedicamentos(String nombre, String categoria,
            double precioInferior, double precioSuperior, int idProveedor,
            String paisProveedor, String vencimiento){
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaVencimiento = LocalDate.parse(vencimiento, formato).plusDays(1);
        // Obtener todos los medicamentos activos.
        Stream<Medicamento> medicamentos = repo.findAllActive().stream()
            .filter(x -> x.getFechaExpira().isBefore(fechaVencimiento));
        // Filtrar
        if(!nombre.isEmpty())
            medicamentos = medicamentos.filter(x -> 
                x.getNombre().toUpperCase().contains(nombre.toUpperCase()));
        if(!categoria.isEmpty())
            medicamentos = medicamentos.filter(x -> 
                x.getCategoria().getNombre().toUpperCase().equals(categoria.toUpperCase()));
        if(precioInferior != -1 && precioSuperior != -1){
            medicamentos = medicamentos.filter(x -> x.getPrecio() >= precioInferior &&
                x.getPrecio() <= precioSuperior);
        }else if(precioInferior == -1 && precioSuperior != -1)
            medicamentos = medicamentos.filter(x -> x.getPrecio() <= precioSuperior);
        if(idProveedor != -1)
            medicamentos = medicamentos.filter(x -> x.getProveedores().stream()
                .map(y -> y.getId()).collect(Collectors.toList()).contains(idProveedor));
        if(!paisProveedor.isEmpty())
            medicamentos = medicamentos.filter(x -> x.getProveedores().stream()
                .map(y -> y.getPais().getCodigo()).collect(Collectors.toList())
                .contains(paisProveedor));
        return medicamentos.collect(Collectors.toList());
    }

    /* Retrieve only one */
    public Medicamento obtenerMedicamento(int id){
        return repo.findById(id).orElse(null);
    }

    /* Update one */
    public Medicamento modificarMedicamento(Medicamento medicamento, int id){
        medicamento.setId(id);
        medicamento.setActivo(1);
        return repo.save(medicamento);
    }

    /* Delete physically */
    public void borrarMedicamento(int id){
        repo.deleteById(id);
    }

    /* Delete logically */
    public void retirarMedicamento(int id){
        Medicamento medicamento = repo.findById(id).orElse(null);
        if(medicamento != null){
            // Lo retiramos
            medicamento.setActivo(0);
            repo.save(medicamento);
        }
    }

    /* Delete logically */
    public void reincorporarMedicamento(int id){
        Medicamento medicamento = repo.findById(id).orElse(null);
        if(medicamento != null){
            // Lo reincorporamos
            medicamento.setActivo(1);
            repo.save(medicamento);
        }
    }
}
