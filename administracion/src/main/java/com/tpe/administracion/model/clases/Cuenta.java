package com.tpe.administracion.model.clases;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Cuenta implements Serializable {

    private Long id;
    private LocalDate fechaAlta;
    private Float saldo;
    private String estado;

    public Cuenta() {}

    public Cuenta(Cuenta c) {
        this.fechaAlta = c.getFechaAlta();
        this.saldo = c.getSaldo();
        this.estado = c.getEstado();
    }

}
