package com.aeditip.springbootcurso.clase2.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.HistorialPrecios;
import com.aeditip.springbootcurso.clase2.entidades.Medicamento;
import com.aeditip.springbootcurso.clase2.repositorios.RepoHistorialPrecios;

@Service
public class ServicioHistorialPrecios {
    @Autowired
    private RepoHistorialPrecios repo;

    public List<HistorialPrecios> obtenerHistorial(int idMedicamento){
        return repo.findByIdMedicamento(idMedicamento);
    }

    public HistorialPrecios crearHistorial(HistorialPrecios historial){
        historial.setFinVigencia(null);
        return repo.save(historial);
    }

    public void actualizarHistorial(Medicamento medicamento, double precioAntiguo){
        // Actualizar el último historial
        // Buscar último historial
        HistorialPrecios ultimoHistorial = repo.findLastHistorialFromMedicamentoId(medicamento.getId());
        // Actualizar el fin de vigencia
        ultimoHistorial.setFinVigencia(LocalDateTime.now());
        // Crear un nuevo historial
        crearHistorial(new HistorialPrecios(medicamento));
    }
}
