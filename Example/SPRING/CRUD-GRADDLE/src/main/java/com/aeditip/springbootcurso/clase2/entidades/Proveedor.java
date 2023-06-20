package com.aeditip.springbootcurso.clase2.entidades;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private int id;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "razon_social")
    private String razonSocial;

    @JoinColumn(name = "domicilio")
    private String domicilio;

    @ManyToOne
    @JoinColumn(name = "pais")
    private Pais pais;

    @Column(name = "activo")
    private int activo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="scm_proveedor_medicamento",
        joinColumns = @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor"),
        inverseJoinColumns = @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento"))
    private List<Medicamento> medicamentos = new ArrayList<Medicamento>();
}
