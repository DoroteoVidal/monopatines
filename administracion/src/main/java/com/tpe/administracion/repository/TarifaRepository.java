package com.tpe.administracion.repository;

import com.tpe.administracion.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query("SELECT t FROM Tarifa t WHERE t.fechaVigencia <= :fecha ORDER BY t.fechaVigencia DESC LIMIT 1")
    public Tarifa obtenerTarifaVigente(LocalDate fecha);

}
