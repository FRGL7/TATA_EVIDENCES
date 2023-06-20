package com.aeditip.springbootcurso.clase2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeditip.springbootcurso.clase2.entidades.HistorialPrecios;

public interface RepoHistorialPrecios extends JpaRepository<HistorialPrecios, Integer> {
    @Query(value = "SELECT * FROM scm_historial_precios WHERE id_medicamento = ?1", nativeQuery = true)
    List<HistorialPrecios> findByIdMedicamento(int idMedicamento);
    @Query(value = "SELECT * FROM scm_historial_precios WHERE id_historial = " +
     "(SELECT MAX(id_historial) FROM scm_historial_precios WHERE id_medicamento = ?1)", nativeQuery = true)
     HistorialPrecios findLastHistorialFromMedicamentoId(int idMedicamento);
}
