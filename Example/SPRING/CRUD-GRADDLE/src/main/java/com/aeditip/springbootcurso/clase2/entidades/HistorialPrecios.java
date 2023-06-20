package com.aeditip.springbootcurso.clase2.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="scm_historial_precios")
public class HistorialPrecios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_historial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    @Column(name="precio")
    private double precio;

    @Column(name="inicio_vigencia")
    private LocalDateTime inicioVigencia;

    @Column(name="fin_vigencia")
    private LocalDateTime finVigencia = null;

    public HistorialPrecios(Medicamento medicamento){
        this.medicamento = medicamento;
        this.precio = medicamento.getPrecio();
        this.inicioVigencia = LocalDateTime.now();
    }
}
