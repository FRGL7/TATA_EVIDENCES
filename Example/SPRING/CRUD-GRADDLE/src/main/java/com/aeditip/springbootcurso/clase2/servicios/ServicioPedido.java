package com.aeditip.springbootcurso.clase2.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeditip.springbootcurso.clase2.entidades.persona.Cliente;
import com.aeditip.springbootcurso.clase2.entidades.persona.Empleado;
import com.aeditip.springbootcurso.clase2.entidades.Pedido;
import com.aeditip.springbootcurso.clase2.entidades.TipoPago;
import com.aeditip.springbootcurso.clase2.entidades.auditoria.Auditoria;
import com.aeditip.springbootcurso.clase2.repositorios.RepoCliente;
import com.aeditip.springbootcurso.clase2.repositorios.RepoEmpleado;
import com.aeditip.springbootcurso.clase2.repositorios.RepoPedido;
import com.aeditip.springbootcurso.clase2.repositorios.RepoTipoPago;

@Service
public class ServicioPedido {
    @Autowired
    private RepoPedido repo;
    @Autowired
    private RepoCliente repoCliente;
    @Autowired
    private RepoEmpleado repoEmpleado;
    @Autowired
    private RepoTipoPago repoTipoPago;

    /* Create */
    public Pedido crearPedido(Pedido pedido, int idCliente, int idEmpleado, int idTipoPago){
        // Buscar el cliente
        Cliente cliente = repoCliente.findById(idCliente).orElse(null);
        Empleado empleado = repoEmpleado.findById(idEmpleado).orElse(null);
        // Buscar el tipo de pago
        TipoPago tipoPago = repoTipoPago.findById(idTipoPago).orElse(null);
        // Asignar al pedido
        if(cliente != null && empleado != null && tipoPago != null){
            pedido.setCliente(cliente);
            pedido.setAtendedor(empleado);
            pedido.setTipoPago(tipoPago);
            pedido.setEstado(1);
            pedido.setActivo(1);
            // Estableciendo auditoría
            Auditoria auditoria = new Auditoria();
            auditoria.setCreatedAt(LocalDateTime.now());
            auditoria.setCreatedBy(empleado);
            pedido.setAuditoria(auditoria);
            // Registrar el pedido
            return repo.save(pedido);
        }
        if(cliente == null)
            System.out.println("No se encontró al cliente N° " + idCliente);
        if(empleado == null)
            System.out.println("No se encontró al empleado N° " + idEmpleado);
        if(tipoPago == null)
            System.out.println("No se encontró al tipo de pago N° " + idTipoPago);
        return null;
    }

    public Pedido modificarPedido(int idPedido, Pedido pedido, int idTipoPago){
        // Recuperar el pedido
        Pedido auxPedido = repo.findById(idPedido).orElse(null);
        if(auxPedido != null){
            // Buscar el tipo de pago
            TipoPago tipoPago = repoTipoPago.findById(idTipoPago).orElse(null);
            // Asignar al pedido
            if(tipoPago != null){
                auxPedido.setTipoPago(tipoPago);
                // Validar antes de establecer en null por error.
                if(pedido.getMonto() != null)
                    auxPedido.setMonto(pedido.getMonto());
                // Editar los registros de auditoría
                Auditoria auditoria = auxPedido.getAuditoria();
                auditoria.setUpdatedAt(LocalDateTime.now());
                auditoria.setUpdatedBy(repoEmpleado.findById(4).get());
                auxPedido.setAuditoria(auditoria);
                // Modificar el pedido
                return repo.save(auxPedido);
            }
        }
        return null;
    }

    /* Retrieve only one */
    public Pedido obtenerPedido(int id){
        return repo.findById(id).orElse(null);
    }

    public List<Pedido> obtenerPedidosporClienteyEstado(int idCliente, int estado){
        if(estado == 0)
            return repo.findAllByClient(idCliente);
        else
        return repo.findAllByClientAndStatus(idCliente, estado);
    }
}
