package com.tpe.mantenimiento.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class DTOReportePorTiempo implements Serializable {
    private Long id;
    private double kmsRecorridos;
    private Long tiempoPausa;

    public DTOReportePorTiempo() {}

    public DTOReportePorTiempo(Long id, double kmsRecorridos, Long tiempoPausa) {
        this.id = id;
        this.kmsRecorridos = kmsRecorridos;
        this.tiempoPausa = tiempoPausa;
    }

}
