package com.tpe.administracion.service;

import com.tpe.administracion.DTO.DTOTarifa;
import com.tpe.administracion.model.clases.Viaje;
import com.tpe.administracion.model.Tarifa;
import com.tpe.administracion.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository repositorio;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Tarifa guardar(Tarifa t) throws Exception {
        try{
            return this.repositorio.save(t);
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Tarifa definirExtra(Long id, Double tarifaExtra) {
        Tarifa actualizada = this.repositorio.findById(id).orElseThrow();
        actualizada.setValorPausaExtendida(tarifaExtra);

        return actualizada;
    }

    @Transactional(readOnly = true)
    public DTOTarifa obtenerPorId(Long id) {
        Tarifa t = this.repositorio.findById(id).orElseThrow();

        return new DTOTarifa(t);
    }

    //Facturacion del año, entre meses
    public ResponseEntity<?> obtenerFacturacionTotalDeAnioEntreMeses(String token, int anio, int mes1, int mes2) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Viaje>> response = restTemplate.exchange(
                "http://localhost:8004/viajes/totalViajesEn/anio/" +anio+ "/entreMes/" +mes1+ "/y/" + mes2,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Viaje>>(){});

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            double total = this.obtenerFacturacion(response.getBody(), anio);

            return ResponseEntity.ok("El total facturado entre los meses " + mes1 + " y " + mes2 + " es: $" + total);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron viajes en esas fechas");
    }

    private double obtenerFacturacion(List<Viaje> viajes, int anio) {
        double total = 0.0;
        final int MINSMAXPAUSA = 15;

        for(Viaje v : viajes) {
            Tarifa t = this.repositorio.obtenerTarifaVigente(this.getFechaALocalDate(v.getFechaHoraInicio()));
            Long duracionViaje = this.obtenerTiempoViajeEnMins(v.getFechaHoraInicio(), v.getFechaHoraFin());
            if(v.getTiempoPausa() > MINSMAXPAUSA) {
                Long duracionInicioPausa = this.obtenerTiempoViajeEnMins(v.getFechaHoraInicio(), v.getInicioPausa());
                total += (duracionInicioPausa + MINSMAXPAUSA) * t.getValor();
                total += (duracionViaje - (duracionInicioPausa + MINSMAXPAUSA)) * t.getValorPausaExtendida();
            }else{
                total += duracionViaje * t.getValor();
            }
        }

        return total;
    }

    private LocalDate getFechaALocalDate(LocalDateTime fecha) {
        return fecha.toLocalDate();
    }

    private Long obtenerTiempoViajeEnMins(LocalDateTime inicio, LocalDateTime fin) {
        return Duration.between(inicio,fin).toMinutes();
    }

}
