package com.tpe.monopatines.repository;

import com.tpe.monopatines.DTO.DTOMonopatinCantViajes;
import com.tpe.monopatines.DTO.DTOMonopatin;
import com.tpe.monopatines.DTO.DTOReportePorTiempo;
import com.tpe.monopatines.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m WHERE m.estado ='disponible' AND (m.latitud BETWEEN :latitudMin AND :latitudMax) " +
                "AND (m.longitud BETWEEN :longitudMin AND :longitudMax)")
    public List<DTOMonopatin> obtenerDisponiblesEnZona(double latitudMin, double latitudMax, double longitudMin, double longitudMax);

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado = :estado")
    public int obtenerCantEnEstado(String estado);

    @Query("SELECT new com.tpe.monopatines.DTO.DTOMonopatinCantViajes(m.id, COUNT(v), YEAR(v.fechaHoraInicio)) FROM Viaje v JOIN v.monopatin m " +
                "WHERE YEAR(v.fechaHoraInicio) = :anio GROUP BY m.id " +
                "HAVING COUNT(v) > :cantViajes")
    public List<DTOMonopatinCantViajes> obtenerConViajesMayoresEnAnio(Long cantViajes, int anio);

    @Query("SELECT new com.tpe.monopatines.DTO.DTOReportePorTiempo(v.monopatin.id, SUM(v.kilometros), SUM(v.tiempoPausa)) " +
                "FROM Viaje v WHERE v.tiempoPausa > 0 GROUP BY v.monopatin.id")
    public List<DTOReportePorTiempo> obtenerReporteConTiempoPausa();

    @Query("SELECT new com.tpe.monopatines.DTO.DTOReportePorTiempo(v.monopatin.id, SUM(v.kilometros), v.tiempoPausa) " +
                "FROM Viaje v WHERE v.tiempoPausa = 0 GROUP BY v.monopatin.id")
    public List<DTOReportePorTiempo> obtenerReporteSinTiempoPausa();

    @Query("SELECT m FROM Monopatin m WHERE m.kmsRecorridos BETWEEN :minKms AND :maxKms ORDER BY m.kmsRecorridos DESC")
    public List<DTOMonopatin> obtenerConKmsEntre(double minKms, double maxKms);

}
