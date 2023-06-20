package com.aeditip.springbootcurso.clase2.entidades.auditoria;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.aeditip.springbootcurso.clase2.entidades.persona.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Auditoria {
    // Llaves foráneas
    @ManyToOne
    private Empleado createdBy;
    @ManyToOne
    private Empleado updatedBy;
    // Campos adicionales
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
