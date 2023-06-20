package com.aeditip.springbootcurso.clase2.entidades.persona;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aeditip.springbootcurso.clase2.entidades.Persona;
import com.aeditip.springbootcurso.clase2.entidades.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_cliente")
public class Cliente extends Persona {
    @Column(name = "direccion")
    private String direccion;

    /*
     * 1) Necesitamos un colección para almacenar los pedidos
     * 2) Tres candidatos: List, Set y Map
     * 3) List: ArrayList -> Facilita el acceso a los elementos
     *      -> Trabaja como si fuera un arreglo.
     * 4) Lo definimos vacío para evitar errores en las solicitudes GET.
     */
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<Pedido>();
}
