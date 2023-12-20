package com.tpe.paradas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    public Parada() {}

    public Parada(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
}
