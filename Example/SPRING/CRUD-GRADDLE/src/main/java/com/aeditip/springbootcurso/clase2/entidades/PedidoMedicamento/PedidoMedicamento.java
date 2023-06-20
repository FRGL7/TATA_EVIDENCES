package com.aeditip.springbootcurso.clase2.entidades.PedidoMedicamento;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Column;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.aeditip.springbootcurso.clase2.entidades.Medicamento;
import com.aeditip.springbootcurso.clase2.entidades.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_pedido_medicamento")
public class PedidoMedicamento {
    @EmbeddedId
    private PedidoMedicamentoKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("pedido")
    @JoinColumn(name="id_pedido")
    private Pedido pedido;

    @JsonIgnore
    @ManyToOne
    @MapsId("medicamento")
    @JoinColumn(name="id_medicamento")
    private Medicamento medicamento;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "descuento")
    private Double descuento;
}
