package com.aeditip.springbootcurso.clase2.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.aeditip.springbootcurso.clase2.entidades.medicamento.CategoriaMedicamento;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_medicamento")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria")
    private CategoriaMedicamento categoria;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "medida")
    private String medida;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha_expiracion")
    private LocalDate fechaExpira;

    @Column(name = "activo")
    private int activo;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="scm_proveedor_medicamento",
        joinColumns = @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento"),
        inverseJoinColumns = @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor"))
    private List<Proveedor> proveedores = new ArrayList<Proveedor>();
}
