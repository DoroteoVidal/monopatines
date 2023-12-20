package com.tpe.paradas.DTO;

import com.tpe.paradas.model.Parada;
import lombok.Data;

import java.io.Serializable;

@Data
public class DTOParada implements Serializable {
    private Long id;
    private Double latitud;
    private Double longitud;

    public DTOParada() {}

    public DTOParada(Parada p) {
        this.id = p.getId();
        this.latitud = p.getLatitud();
        this.longitud = p.getLongitud();
    }

}
