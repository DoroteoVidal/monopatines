package com.tpe.mantenimiento.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Monopatin implements Serializable {
    private Long id;
    private double kmsRecorridos;
    private double latitud;
    private double longitud;
    private Long idGps;
    private String estado;

    public Monopatin() {}

    public Monopatin(Monopatin m) {
        this.id = m.getId();
        this.kmsRecorridos = m.getKmsRecorridos();
        this.latitud = m.getLatitud();
        this.longitud = m.getLongitud();
        this.idGps = m.getIdGps();
        this.estado = m.getEstado();
    }
    
}
