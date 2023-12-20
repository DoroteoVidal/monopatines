package com.tpe.monopatines.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private double kmsRecorridos;

    @Column(nullable = false)
    private Long idGps;

    @Column(nullable = false)
    private double latitud;

    @Column(nullable = false)
    private double longitud;

    public Monopatin() {}

    public Monopatin(String estado, double kmsRecorridos, Long idGps, double latitud, double longitud) {
        this.estado = estado;
        this.kmsRecorridos = kmsRecorridos;
        this.idGps = idGps;
        this.latitud = latitud;
        this.longitud = longitud;
    }

}
