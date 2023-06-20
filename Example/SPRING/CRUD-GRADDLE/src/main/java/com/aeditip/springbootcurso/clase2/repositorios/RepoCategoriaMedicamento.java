package com.aeditip.springbootcurso.clase2.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeditip.springbootcurso.clase2.entidades.medicamento.CategoriaMedicamento;

public interface RepoCategoriaMedicamento extends JpaRepository<CategoriaMedicamento, Integer> {
    @Query(value = "SELECT * FROM scm_categoria_medicamento WHERE nombre = ?1", nativeQuery = true)
    Optional<CategoriaMedicamento> findByNombre(String nombre);
}
