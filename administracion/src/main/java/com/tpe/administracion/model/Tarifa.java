package com.tpe.administracion.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String clave;

    @Column
    private Double valor;

    @Column
    private Double valorPausaExtendida;

    @Column
    private LocalDate fechaVigencia;

    public Tarifa() {}

    public Tarifa(String clave, Double valor, Double valorPausaExtendida, LocalDate fechaVigencia) {
        this.clave = clave;
        this.valor = valor;
        this.valorPausaExtendida = valorPausaExtendida;
        this.fechaVigencia = fechaVigencia;
    }

}
