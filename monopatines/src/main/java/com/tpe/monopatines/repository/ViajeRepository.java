package com.tpe.monopatines.repository;

import com.tpe.monopatines.DTO.DTOViaje;
import com.tpe.monopatines.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE " +
            "YEAR(v.fechaHoraInicio) = :anio AND MONTH(v.fechaHoraInicio) BETWEEN :mes1 AND :mes2")
    public List<DTOViaje> getTotalEnAnioEntreMeses(int anio, int mes1, int mes2);
    
}
