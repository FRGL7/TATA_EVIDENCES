package com.aeditip.springbootcurso.clase2.entidades;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aeditip.springbootcurso.clase2.entidades.PedidoMedicamento.PedidoMedicamento;
import com.aeditip.springbootcurso.clase2.entidades.auditoria.Auditoria;
import com.aeditip.springbootcurso.clase2.entidades.persona.Cliente;
import com.aeditip.springbootcurso.clase2.entidades.persona.Empleado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scm_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;

    // Para llaves foráneas
    @JsonIgnore
    @ManyToOne  // M:1
    @JoinColumn(name = "id_cliente") // id_cliente es la FK
    // En una llave foránea, debe haber referencia a una entidad (@Entity)
    private Cliente cliente;

    @ManyToOne  // M:1
    @JoinColumn(name = "id_empleado") // id_cliente es la FK
    // En una llave foránea, debe haber referencia a una entidad (@Entity)
    private Empleado atendedor;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "tipo_pago")
    private TipoPago tipoPago;

    @Column(name = "estado")
    private int estado;

    @Column(name = "activo")
    private int activo;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoMedicamento> medicamentos;

    // Agregar la tabla falsa de auditoría (clases embebidas)
    @Embedded
    // Llaves foráneas
    @AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns=@JoinColumn(name="created_by")),
        @AssociationOverride(name = "updatedBy", joinColumns=@JoinColumn(name="updated_by"))
    })
    // Campos adicionales
    @AttributeOverrides({
        @AttributeOverride(name="createdAt", column=@Column(name="created_at")),
        @AttributeOverride(name="updatedAt", column=@Column(name="updated_at"))
    })
    private Auditoria auditoria;

}
