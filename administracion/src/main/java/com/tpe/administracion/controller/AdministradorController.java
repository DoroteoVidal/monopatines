package com.tpe.administracion.controller;

import com.tpe.administracion.AuthorityConstant;
import com.tpe.administracion.model.clases.Monopatin;
import com.tpe.administracion.model.clases.Parada;
import com.tpe.administracion.model.Tarifa;
import com.tpe.administracion.service.AdministradorService;
import com.tpe.administracion.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("administradores")

public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private TarifaService tarifaService;

    //ABM monopatines
    @Operation(summary = "Guarda un monopatin.", description = "Agrega un nuevo monopatin en el servicio de monopatines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PostMapping("/monopatines")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> guardarMonopatin(@RequestHeader("Authorization") String token, @RequestBody Monopatin m) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.guardarMonopatin(token, m));
    }

    @Operation(summary = "Eliminar un monopatin.", description = "Elimina un monopatin segun su ID del servicio monopatines.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @DeleteMapping("/monopatines/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> eliminarMonopatin(@RequestHeader("Authorization") String token, @PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(administradorService.eliminarMonopatin(token, id));
    }

    @Operation(summary = "Cantidad de monopatines en mantenimiento vs en operacion",
            description = "Obtiene la cantidad de monopatines en mantenimiento vs en operacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/monopatines/reporteMonopatinesOpVsMant")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> obtenerReporteMonopatinMantenimientoVsOperando(@RequestHeader("Authorization") String token) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.obtenerReporteMonopatinMantenimientoVsOperando(token));
    }

    @Operation(summary = "Listado de monopatines con la cantidad de viajes en un año dado.",
            description = "Obtiene los monopatines de un año dado que tengan una cantidad de viajes mayor a los dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/monopatines/anio/{anio}/cantViajesMayorA/{cant_viajes}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> obtenerMonopatinesConViajesMayoresEnAnio(@RequestHeader("Authorization") String token, @PathVariable int anio, @PathVariable Long cantViajes) {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.obtenerMonopatinesConViajesMayoresEnAnio(token, anio, cantViajes));
    }

    @Operation(summary = "Listado de monopatines con kms entre un minimo y maximo dado",
            description = "Obtiene un listado de monopatines con una cantidad de kms entre un minimo y maximo dado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/monopatines/conKmsEntre/min/{minKms}/max/{maxKms}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> obtenerMonopatinesConKmsEntre(@RequestHeader("Authorization") String token, @PathVariable double minKms, @PathVariable double maxKms) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.obtenerMonopatinesConKmsEntre(token, minKms, maxKms));
    }

    //ABM paradas
    @Operation(summary = "Guardad parada", description = "Crea una nueva parada en el servicio de paradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PostMapping("/paradas")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> guardarParada(@RequestHeader("Authorization") String token, @RequestBody Parada p) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.guardarParada(token, p));
    }

    @Operation(summary = "Eliminar parada", description = "Elimina una parada segun su ID en el servicio de paradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @DeleteMapping("/paradas/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> eliminarParada(@RequestHeader("Authorization") String token, @PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(administradorService.eliminarParada(token, id));
    }

    //Anular cuenta momentaneamente
    @Operation(summary = "Anular una cuenta", description = "Anula una cuenta segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PutMapping("/cuentas/anular/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> anularCuentaMomentaneamente(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.anularCuentaMomentaneamente(token, id));
    }

    //agregar una nueva tarifa
    @Operation(summary = "Guardar una Tarifa.", description = "Agrega una nueva tarifa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PostMapping("/tarifas")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> guardarTarifa(@RequestBody Tarifa t) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.guardar(t));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Revise los campos y vuelva a intentar.");
        }
    }

    //Total facturado
    @Operation(summary = "Facturacion total en un periodo de tiempo dado.",
            description = "Calcula la facturacion total entre dos meses de un año dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/tarifas/totalFacturadoEnAnio/{anio}/entreMes/{mes1}/y/{mes2}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> obtenerFacturacionTotalDeAnioEntreMeses(@RequestHeader("Authorization") String token, @PathVariable int anio, @PathVariable int mes1, @PathVariable int mes2) {
        return ResponseEntity.status(HttpStatus.OK).body(tarifaService.obtenerFacturacionTotalDeAnioEntreMeses(token, anio, mes1, mes2));
    }
}
