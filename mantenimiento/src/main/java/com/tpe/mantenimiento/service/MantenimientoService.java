package com.tpe.mantenimiento.service;

import com.tpe.mantenimiento.model.Monopatin;
import com.tpe.mantenimiento.DTO.DTOReportePorTiempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.tpe.mantenimiento.model.Mantenimiento;
import com.tpe.mantenimiento.DTO.DTOMantenimiento;
import com.tpe.mantenimiento.repository.MantenimientoRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MantenimientoService {
	
	@Autowired
	private MantenimientoRepository mantenimientoRepository;
    @Autowired
    private RestTemplate restTemplate;
	
	@Transactional
    public Mantenimiento guardar(Mantenimiento m) throws Exception {
        try{
            return this.mantenimientoRepository.save(m);
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DTOMantenimiento actualizar(String id, DTOMantenimiento m) throws Exception {
        try{
            if(this.mantenimientoRepository.existsById(id)) {
                Mantenimiento antiguo = this.mantenimientoRepository.findById(id).get();
                antiguo.setIdMonopatin(m.getIdMonopatin());
                antiguo.setFechaInicio(m.getFechaInicio());
                antiguo.setFechaFin(m.getFechaFin());
                antiguo.setEstaReparado(m.isEstaReparado());
                this.mantenimientoRepository.save(antiguo);

                return new DTOMantenimiento(antiguo);
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean eliminar(String id) throws Exception {
        try{
            if(this.mantenimientoRepository.existsById(id)) {
                this.mantenimientoRepository.deleteById(id);

                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DTOMantenimiento obtenerPorId(String id) throws Exception {
        try{
            return new DTOMantenimiento(this.mantenimientoRepository.findById(id).orElseThrow());
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> enviarMonopatinMantenimiento(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Monopatin> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/" + id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>() {});

        if(response.getStatusCode().is2xxSuccessful()) {
            Monopatin monopatin = response.getBody();
            if(monopatin.getEstado().equals("disponible")) {
                Mantenimiento mantenimiento = new Mantenimiento(id, LocalDateTime.now(), null, false);
                this.mantenimientoRepository.save(mantenimiento);
                monopatin.setEstado("en mantenimiento");

                HttpEntity<Monopatin> requestEntity2 = new HttpEntity<>(monopatin, headers);
                ResponseEntity<Monopatin> response2 = restTemplate.exchange(
                        "http://localhost:8004/monopatines/" + id,
                        HttpMethod.PUT,
                        requestEntity2,
                        new ParameterizedTypeReference<Monopatin>(){});

                return response2;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin que intenta añadir a mantenimiento no fue encontrado");
    }

    public ResponseEntity<?> sacarMonopatinDeMantenimiento(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Monopatin> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/" + id,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Monopatin>(){});

        if(response.getStatusCode().is2xxSuccessful()) {
            Monopatin monopatin = response.getBody();
            if(monopatin.getEstado().equals("en mantenimiento")) {
                Mantenimiento mantenimiento = this.mantenimientoRepository.buscarPorIdMonopatinYReparado(id, false);
                if(mantenimiento != null) {
                    mantenimiento.setEstaReparado(true);
                    mantenimiento.setFechaFin(LocalDateTime.now());
                    this.mantenimientoRepository.save(mantenimiento);
                    monopatin.setEstado("disponible");

                    HttpEntity<Monopatin> requestEntity2 = new HttpEntity<>(monopatin, headers);
                    ResponseEntity<Monopatin> response2 = restTemplate.exchange(
                            "http://localhost:8004/monopatines/" + id,
                            HttpMethod.PUT,
                            requestEntity2,
                            new ParameterizedTypeReference<Monopatin>(){});

                    return response2;
                }
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin que intenta sacar de mantenimiento no fue encontrado");
    }

    public ResponseEntity<?> obtenerReporteMonopatinesConTiempoPausa(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<DTOReportePorTiempo>> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/reporte/conTiempoPausa",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<DTOReportePorTiempo>>(){});

        if(response != null) {
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. No se encontraron monopatines con tiempo de pausa");
    }

    public ResponseEntity<?> obtenerReporteMonopatinesSinTiempoPausa(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<DTOReportePorTiempo>> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/reporte/sinTiempoPausa",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<DTOReportePorTiempo>>(){});

        if(response != null) {
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. No se encontraron monopatines sin tiempo de pausa");
    }

}
