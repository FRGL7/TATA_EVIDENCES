package com.aeditip.springbootcurso.clase2.controladores;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.aeditip.springbootcurso.clase2.entidades.HistorialPrecios;
import com.aeditip.springbootcurso.clase2.entidades.Medicamento;
import com.aeditip.springbootcurso.clase2.servicios.ServicioCategoriaMedicamento;
import com.aeditip.springbootcurso.clase2.servicios.ServicioHistorialPrecios;
import com.aeditip.springbootcurso.clase2.servicios.ServicioMedicamento;
import com.aeditip.springbootcurso.clase2.servicios.ServicioPais;
import com.aeditip.springbootcurso.clase2.servicios.ServicioProveedor;

@RestController
@RequestMapping("/api/medicamentos")
public class ControladorMedicamento {
    @Autowired
    private ServicioMedicamento servicio;
    @Autowired
    private ServicioHistorialPrecios servicioHistorial;
    @Autowired
    private ServicioPais servicioPais;
    @Autowired
    private ServicioProveedor servicioProveedor;
    @Autowired
    private ServicioCategoriaMedicamento servicioCategoriaMedicamento;

    /* Create -> POST */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Medicamento crearMedicamento(@RequestBody Medicamento medicamento){
        Medicamento objMedicamento =  servicio.crearMedicamento(medicamento);
        // Crear un historial
        HistorialPrecios historial = new HistorialPrecios(medicamento);
        servicioHistorial.crearHistorial(historial);
        return objMedicamento;
    }

    /* Retrieve all -> GET */
    @Operation(summary = "Obtener todos los medicamentos, dados ciertos filtros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
        content = {@Content(mediaType = "application/json")})
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Medicamento>> obtenerMedicamentos(HttpServletResponse response, 
            @Parameter(description = "Nombre del medicamento") @RequestParam(defaultValue = "") String nombre,
            @Parameter(description = "Categoría del medicamento") @RequestParam(defaultValue = "") String categoria,
            @Parameter(description = "Rango del precio medicamento (mínimo)") @RequestParam(defaultValue = "-1") double precioInferior,
            @Parameter(description = "Rango del precio medicamento (máximo)") @RequestParam(defaultValue = "-1") double precioSuperior,
            @Parameter(description = "Id de un proveedor") @RequestParam(defaultValue = "-1") int idProveedor,
            @Parameter(description = "Código del país de un proveedor") @RequestParam(defaultValue = "") String paisProveedor,
            @Parameter(description = "Fecha límite de vencimiento") @RequestParam(defaultValue = "") String fechaVencimiento){
        boolean ocurrioError = false;
        String motivoError = "";

        if(idProveedor != -1 && !paisProveedor.isEmpty()){
            motivoError += "No puede tener el ID y país del proveedor para buscar.";
            ocurrioError = true;
        }

        // Categoría no existe
        if(!categoria.isEmpty() && 
                servicioCategoriaMedicamento.obtenerCategoriaPorNombre(categoria) == null){
            motivoError += "La categoría a filtrar no existe.";
            ocurrioError = true;
        }

        // País no existe
        if(!paisProveedor.isEmpty() && 
                servicioPais.obtenerPais(paisProveedor) == null){
            motivoError += "El país a filtrar no existe.";
            ocurrioError = true;
        }
        // Proveedor no existe
        if(idProveedor != -1 && 
                servicioProveedor.obtenerProveedor(idProveedor) == null){
            motivoError += "El proveedor a filtrar no existe.";
            ocurrioError = true;
        }

        // Rango de precios: que haya filtro inferior, pero no superior
        if(precioInferior != -1 && precioSuperior == -1){
            motivoError += "El rango de precios no es válido.";
            ocurrioError = true;
        }

        if(ocurrioError){
            response.addHeader("Error-Reason", motivoError);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<Medicamento> medicamentos = servicio.obtenerMedicamentos
            (nombre, categoria, precioInferior, precioSuperior, idProveedor,
                paisProveedor, fechaVencimiento);
        return new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    /* Retrieve only one -> GET */
    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Medicamento obtenerMedicamento(HttpServletResponse response, 
            @PathVariable int id){
        Medicamento medicamento = servicio.obtenerMedicamento(id);
        if(medicamento == null){
            response.setStatus(404);
            response.addHeader("Denegation-Reason", "No se encontró el medicamento buscado");
        } else {
            response.setStatus(200);
        }
        return medicamento;  // ResponseBody
    }

    @RequestMapping(value="/historial-precios/{idMedicamento}", method=RequestMethod.GET)
    public List<HistorialPrecios> obtenerHistorial(@PathVariable int idMedicamento){
        return servicioHistorial.obtenerHistorial(idMedicamento);
    }

    /* Update one -> PATCH/PUT */
    @RequestMapping(value="{id}", method = RequestMethod.PATCH)
    public Medicamento modificarMedicamento(@RequestBody Medicamento medicamento,
            @PathVariable int id){
        double precioAntiguo = servicio.obtenerMedicamento(id).getPrecio();
        medicamento = servicio.modificarMedicamento(medicamento, id);
        // Si el precio ha sido modificado, establecer nuevo historial;
        if(medicamento.getPrecio() != precioAntiguo)
            servicioHistorial.actualizarHistorial(medicamento, precioAntiguo);
        return medicamento;
    }

    /* Delete physically -> DELETE */
    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void borrarMedicamento(@PathVariable int id){
        servicio.borrarMedicamento(id);
    }

    /* Delete logically -> PATCH/PUT */
    @RequestMapping(value="retirar/{id}", method = RequestMethod.PATCH)
    public void retirarMedicamento(@PathVariable int id){
        servicio.retirarMedicamento(id);
    }

    /* Delete logically -> PATCH/PUT */
    @RequestMapping(value="reincorporar/{id}", method = RequestMethod.PATCH)
    public void reincorporarMedicamento(@PathVariable int id){
        servicio.reincorporarMedicamento(id);
    }
}
