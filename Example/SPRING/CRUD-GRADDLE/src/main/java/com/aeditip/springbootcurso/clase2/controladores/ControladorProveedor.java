package com.aeditip.springbootcurso.clase2.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeditip.springbootcurso.clase2.entidades.Proveedor;
import com.aeditip.springbootcurso.clase2.servicios.ServicioProveedor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/proveedores")
public class ControladorProveedor {
    @Autowired
    private ServicioProveedor servicio;

    @Operation(summary = "Crear un proveedor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor registrado",
        content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Proveedor.class))})
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    private Proveedor crearProveedor(@RequestBody Proveedor proveedor,
            @Parameter(description = "Código del país del proveedor") @RequestParam String codPais){
        return servicio.crearProveedor(proveedor, codPais);
    }

    @Operation(summary = "Obtener todos los proveedores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
        content = {@Content(mediaType = "application/json")})
    })
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<Proveedor> obtenerProveedores() {
        return servicio.obtenerProveedores();
    }
    
}
