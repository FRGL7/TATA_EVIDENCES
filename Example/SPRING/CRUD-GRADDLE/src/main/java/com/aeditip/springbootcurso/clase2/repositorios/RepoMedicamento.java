package com.aeditip.springbootcurso.clase2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeditip.springbootcurso.clase2.entidades.Medicamento;

/* JpaRepository<T, ID>:
 * Donde:
 * - T: Entidad (Template)
 * - ID: Tipo de dato del PK de la entidad
 * 
 * Dimensions:
 * - int -> Integer
 * - double -> Double
 * - boolean -> Boolean
*/

public interface RepoMedicamento extends JpaRepository<Medicamento, Integer> {
    @Query(value="SELECT * FROM scm_medicamento WHERE activo = 1", nativeQuery=true)
    List<Medicamento> findAllActive();
}
