package com.aeditip.springbootcurso.clase2.entidades.PedidoMedicamento;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PedidoMedicamentoKey implements Serializable {
    @Column(name="id_pedido")
    private int pedido;

    @Column(name="id_medicamento")
    private int medicamento;
}
