package com.tpe.usuarios.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import com.tpe.usuarios.model.Cuenta;

import lombok.Data;

@Data
public class DTOCuenta implements Serializable {
    private Long id;
    private LocalDate fechaDeAlta;
    private Float saldo;
    private String estado;

    public DTOCuenta() {}

    public DTOCuenta(Cuenta c) {
        this.fechaDeAlta = c.getFechaDeAlta();
        this.saldo = c.getSaldo();
        this.estado = c.getEstado();
    }
    
}
