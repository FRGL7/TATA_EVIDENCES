package com.aeditip.springbootcurso.clase2.entidades.persona;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aeditip.springbootcurso.clase2.entidades.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_empleado")
public class Empleado extends Persona {
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEmpleado rol;

    // Código de profesional farmaceútico.
    @Column(name = "cqfp")
    private Integer codigoCQFP;

    public String obtenerCodigo(){
        return "CQFP-" + this.codigoCQFP;
    }
}
