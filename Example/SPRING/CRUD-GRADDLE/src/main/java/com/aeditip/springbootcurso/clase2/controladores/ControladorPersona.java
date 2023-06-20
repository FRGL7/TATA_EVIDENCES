package com.aeditip.springbootcurso.clase2.controladores;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aeditip.springbootcurso.clase2.entidades.persona.Cliente;
import com.aeditip.springbootcurso.clase2.entidades.Persona;
import com.aeditip.springbootcurso.clase2.servicios.ServicioPersona;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
public class ControladorPersona {
    @Autowired
    private ServicioPersona servicio;

    @Operation(summary = "Buscar un cliente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
        content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Persona.class))}),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
        content = {@Content()})
    })
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Persona obtenerCliente(@Parameter(description = "ID del cliente objetivo")
            @PathVariable int id, HttpServletResponse response){
        Persona cliente = servicio.obtenerCliente(id);
        if(cliente == null){
            response.setStatus(404);
            return null;
        }
        return servicio.obtenerCliente(id);
    }

    @Operation(summary = "Obtener todos los clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
        content = {@Content(mediaType = "application/json")})
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Cliente> obtenerClientes(){
        return servicio.obtenerCientes();
    }
}
