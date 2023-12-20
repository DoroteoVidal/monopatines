package com.tpe.administracion.model.clases;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Viaje implements Serializable {

    private Long id;
    private Long idMonopatin;
    private Long idUsuario;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private double kilometros;
    private Long tiempoPausa;
    private LocalDateTime inicioPausa;
    private boolean enPausa;

    public Viaje() {}

    public Viaje(Viaje v) {
        this.id = v.getId();
        this.idMonopatin = v.getIdMonopatin();
        this.idUsuario = v.getIdUsuario();
        this.fechaHoraInicio = v.getFechaHoraInicio();
        this.fechaHoraFin = v.getFechaHoraFin();
        this.kilometros = v.getKilometros();
        this.tiempoPausa = v.getTiempoPausa();
        this.inicioPausa = v.getInicioPausa();
        this.enPausa = v.isEnPausa();
    }

}
