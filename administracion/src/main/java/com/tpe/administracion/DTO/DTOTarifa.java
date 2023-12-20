package com.tpe.administracion.DTO;

import com.tpe.administracion.model.Tarifa;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DTOTarifa implements Serializable {

    private Long id;
    private String clave;
    private Double valor;
    private Double valorPausaExtendida;
    private LocalDate fechaVigencia;

    public DTOTarifa() {}

    public DTOTarifa(Tarifa t) {
        this.id = t.getId();
        this.clave = t.getClave();
        this.valor = t.getValor();
        this.valorPausaExtendida = t.getValorPausaExtendida();
        this.fechaVigencia = t.getFechaVigencia();
    }

}
