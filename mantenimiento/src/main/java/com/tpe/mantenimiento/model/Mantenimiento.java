package com.tpe.mantenimiento.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "coleccion_mantenimiento")
public class Mantenimiento {

	@Id
	private String id;
	private Long idMonopatin;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private boolean estaReparado;

	public Mantenimiento(Long idMonopatin, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean estaReparado) {
		this.idMonopatin = idMonopatin;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estaReparado = estaReparado;
	}

}
