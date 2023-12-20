package com.tpe.administracion.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class DTOMonopatinCantViajes implements Serializable {

    private Long id;
    private Long cantViajes;
    private int anio;

    public DTOMonopatinCantViajes() {}

    public DTOMonopatinCantViajes(Long id, Long cantViajes, int anio) {
        this.id = id;
        this.cantViajes = cantViajes;
        this.anio = anio;
    }

}
