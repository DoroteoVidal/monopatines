package com.tpe.usuarios.controller;

import com.tpe.usuarios.AuthorityConstant;
import com.tpe.usuarios.DTO.DTOCuenta;
import com.tpe.usuarios.model.Cuenta;
import com.tpe.usuarios.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cuentas")
public class CuentaController {
    @Autowired
    private CuentaService service;

    @Operation(summary = "Crear una cuenta", description = "Agrega una nueva cuenta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PostMapping("")
    public ResponseEntity<?> guardarCuenta(@RequestBody Cuenta cuenta) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cuenta));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. revise los campos e intente nuevamente.");
        }
    }

    @Operation(summary = "Actualizar cuenta", description = "Actualiza una cuenta segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable Long id, @RequestBody DTOCuenta cuenta) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizar(id, cuenta));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. No se ha podido actualizar la cuenta que desea.");
        }
    }

    @Operation(summary = "Anular una cuenta", description = "Anula una cuenta segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN")
    })
    @PutMapping("/anular/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.anularCuenta(id));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. No se ha podido anular la cuenta que desea.");
        }
    }

    @Operation(summary = "Obtener cuenta", description = "Obtiene una cuenta segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ADMIN + "\" )")
    public ResponseEntity<?> obtenerCuentaPorId(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPorId(id));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. La cuenta que está buscando, no fue encontrada");
        }
    }
}
