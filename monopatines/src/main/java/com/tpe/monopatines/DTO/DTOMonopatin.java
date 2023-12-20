package com.tpe.monopatines.DTO;

import com.tpe.monopatines.model.Monopatin;

import lombok.Data;

import java.io.Serializable;

@Data
public class DTOMonopatin implements Serializable {
    private Long id;
    private String estado;
    private double kmsRecorridos;
    private Long idGps;
    private double latitud;
    private double longitud;

    public DTOMonopatin() {}

    public DTOMonopatin(Monopatin m) {
        this.id = m.getId();
        this.estado = m.getEstado();
        this.kmsRecorridos = m.getKmsRecorridos();
        this.idGps = m.getIdGps();
        this.latitud = m.getLatitud();
        this.longitud = m.getLongitud();
    }

}
