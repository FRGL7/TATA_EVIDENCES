package com.aeditip.springbootcurso.clase2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeditip.springbootcurso.clase2.entidades.Pedido;
import com.aeditip.springbootcurso.clase2.servicios.ServicioPedido;

@RestController
@RequestMapping("/api/pedidos")
public class ControladorPedido {
    @Autowired
    private ServicioPedido servicioPedido;

    @RequestMapping(value="", method = RequestMethod.POST)
    public Pedido crearPedido(@RequestBody Pedido pedido, @RequestParam int idCliente,
            @RequestParam int idEmpleado, @RequestParam int idTipoPago){
        return servicioPedido.crearPedido(pedido, idCliente, idEmpleado, idTipoPago);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PATCH)
    public Pedido crearPedido(@PathVariable int id, @RequestBody Pedido pedido,
                @RequestParam int idTipoPago){
        return servicioPedido.modificarPedido(id, pedido, idTipoPago);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Pedido obtenerPedido(@PathVariable int id){
        return servicioPedido.obtenerPedido(id);
    }

    @RequestMapping(value="/lista/{idCliente}", method = RequestMethod.GET)
    public List<Pedido> obtenerPedidosporClienteyEstado(@PathVariable int idCliente,
            @RequestParam(defaultValue = "0") int estado){
        return servicioPedido.obtenerPedidosporClienteyEstado(idCliente, estado);
    }
}
