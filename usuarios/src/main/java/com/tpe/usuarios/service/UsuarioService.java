package com.tpe.usuarios.service;

import com.tpe.usuarios.model.Cuenta;
import com.tpe.usuarios.model.Monopatin;
import com.tpe.usuarios.model.Rol;
import com.tpe.usuarios.DTO.DTOUsuario;
import com.tpe.usuarios.model.Usuario;
import com.tpe.usuarios.repository.CuentaRepository;
import com.tpe.usuarios.repository.RolRepository;
import com.tpe.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UsuarioService {
    
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private CuentaRepository cuentaRepository;
    @Autowired private RolRepository rolRepository;
    @Autowired private RestTemplate restTemplate;
    @Autowired private PasswordEncoder passwordEncoder;

    @Transactional
    public DTOUsuario guardar(DTOUsuario u) throws Exception{
        try{
            if(this.cuentaRepository.existsById(u.getIdCuenta()) && this.rolRepository.existsById(u.getRol())){
                Cuenta c = this.cuentaRepository.findById(u.getIdCuenta()).get();
                Rol rol = this.rolRepository.findById(u.getRol()).get();
                String encryptedPass = passwordEncoder.encode(u.getPassword());
                Usuario nuevo = new Usuario(u.getEmail(), u.getTelefono(), u.getNombre(), u.getApellido(), c, rol, encryptedPass);
                this.usuarioRepository.save(nuevo);

                return new DTOUsuario(nuevo);
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DTOUsuario obtenerPorId(Long id) throws Exception {
        try{
            return new DTOUsuario(this.usuarioRepository.findById(id).orElseThrow());
        }catch(Exception e){
            throw e;
        }
    }

    public ResponseEntity<?> getMonopatinesDisponiblesEnZona(double latitud, double longitud) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<Monopatin>> response = restTemplate.exchange(
                "http://localhost:8004/monopatines/disponiblesEnZona/latitud/" + latitud + "/longitud/" + longitud,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Monopatin>>(){});

        if(response.getStatusCode().is2xxSuccessful()){
            return response;
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron monopatines disponibles en su zona");
        }
    }

}
