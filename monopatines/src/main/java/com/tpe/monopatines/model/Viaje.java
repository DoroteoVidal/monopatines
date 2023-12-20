package com.tpe.monopatines.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_monopatin")
    private Monopatin monopatin;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(nullable = false)
    private double kilometros;

    @Column(nullable = false)
    private Long tiempoPausa;

    @Column
    private LocalDateTime inicioPausa;

    @Column(nullable = false)
    private boolean enPausa;

    public Viaje() {}

    public Viaje(Monopatin monopatin, Long idUsuario, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin,
                 double kilometros, Long tiempoPausa, LocalDateTime inicioPausa, boolean enPausa) {
        this.monopatin = monopatin;
        this.idUsuario = idUsuario;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.kilometros = kilometros;
        this.tiempoPausa = tiempoPausa;
        this.inicioPausa = inicioPausa;
        this.enPausa = enPausa;
    }
    
}
