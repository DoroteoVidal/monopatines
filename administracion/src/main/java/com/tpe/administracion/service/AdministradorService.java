package com.tpe.administracion.service;

import com.tpe.administracion.DTO.DTOMonopatinCantViajes;
import com.tpe.administracion.model.clases.Monopatin;
import com.tpe.administracion.model.clases.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Service
public class AdministradorService {

    @Autowired
    private RestTemplate restTemplate;

    //ABM monopatines
    public ResponseEntity<?> guardarMonopatin(String token, Monopatin m) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        Monopatin nuevo = new Monopatin(m);
        HttpEntity<Monopatin> requestEntity = new HttpEntity<>(nuevo, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8004/monopatines",
                HttpMethod.POST,
                requestEntity,
                String.class);

        return response;
    }

    public ResponseEntity<?> eliminarMonopatin(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        return response;
    }

    //Reportes monopatines
    public ResponseEntity<?> obtenerReporteMonopatinMantenimientoVsOperando(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/reporte/enMantenimientoVsOperando",
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

        if(response != null) {
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. No se encontraron monopatines con las caracteristicas ingresadas,");
    }

    public ResponseEntity<?> obtenerMonopatinesConViajesMayoresEnAnio(String token, int anio, Long cantViajes) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requesEntity = new HttpEntity<>(headers);
        ResponseEntity<List<DTOMonopatinCantViajes>> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/anio/" +anio+ "/cantViajesMayorA/" + cantViajes,
                HttpMethod.GET,
                requesEntity,
                new ParameterizedTypeReference<List<DTOMonopatinCantViajes>>(){});

        if(response != null) {
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. No se encontraron monopatines con las caracteristicas ingresadas,");
    }

    public ResponseEntity<?> obtenerMonopatinesConKmsEntre(String token, double minKms, double maxKms) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requesEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Monopatin>> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/conKmsEntre/min/" +minKms+ "/max/" +maxKms,
                HttpMethod.GET,
                requesEntity,
                new ParameterizedTypeReference<List<Monopatin>>(){});

        if(response != null) {
            return response;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. No se encontraron monopatines con las caracteristicas ingresadas,");

    }

    //ABM paradas
    public ResponseEntity<?> guardarParada(String token, Parada p) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        Parada nueva = new Parada(p);
        HttpEntity<Parada> requestEntity = new HttpEntity<>(nueva, headers);
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:8005/paradas",
                HttpMethod.POST,
                requestEntity,
                String.class);

        return response;
    }

    public ResponseEntity<?> eliminarParada(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8005/paradas/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        return response;
    }

    //Anular cuenta momementaneamente
    public ResponseEntity<?> anularCuentaMomentaneamente(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8001/cuentas/anular/" +id,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class);

        if(response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("La cuenta fue anulada");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cuenta que desea anular no fue encontrada");
    }

}




