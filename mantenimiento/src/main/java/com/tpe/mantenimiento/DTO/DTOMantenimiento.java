package com.tpe.mantenimiento.DTO;

import com.tpe.mantenimiento.model.Mantenimiento;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DTOMantenimiento implements Serializable {
    private String id;
    private Long idMonopatin;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean estaReparado;

    public DTOMantenimiento() {}

    public DTOMantenimiento(Mantenimiento m) {
        this.id = m.getId();
        this.idMonopatin = m.getIdMonopatin();
        this.fechaInicio = m.getFechaInicio();
        this.fechaFin = m.getFechaFin();
        this.estaReparado = m.isEstaReparado();
    }
    
}
