package com.tpe.usuarios.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Monopatin implements Serializable {
    private Long id;
    private String estado;
    private double kmsRecorridos;
    private Long idGps;
    private double latitud;
    private double longitud;

    public Monopatin() {}

    public Monopatin(Monopatin m) {
        this.id = m.getId();
        this.estado = m.getEstado();
        this.kmsRecorridos = m.getKmsRecorridos();
        this.idGps = m.getIdGps();
        this.latitud = m.getLatitud();
        this.longitud = m.getLongitud();
    }

}
