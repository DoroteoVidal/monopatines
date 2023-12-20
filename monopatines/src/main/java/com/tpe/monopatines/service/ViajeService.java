package com.tpe.monopatines.service;


import com.tpe.monopatines.DTO.DTOViaje;
import com.tpe.monopatines.model.Monopatin;
import com.tpe.monopatines.model.Viaje;
import com.tpe.monopatines.repository.MonopatinRepository;
import com.tpe.monopatines.repository.ViajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ViajeService {

    @Autowired
    private ViajeRepository repository;
    @Autowired
    private MonopatinRepository repo_mono;

    @Transactional
    public DTOViaje guardar(DTOViaje v) {
        try{
            Monopatin m = this.repo_mono.findById(v.getIdMonopatin()).get();
            Viaje nuevo = new Viaje(m, v.getIdUsuario(), v.getFechaHoraInicio(), v.getFechaHoraFin(),
                v.getKilometros(), v.getTiempoPausa(),v.getInicioPausa(), v.isEnPausa());
            this.repository.save(nuevo);

            return new DTOViaje(nuevo);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOViaje> getTotalEnAnioEntreMeses(int anio, int mes1, int mes2) {
        try{
            return repository.getTotalEnAnioEntreMeses(anio, mes1, mes2);
        }catch(Exception e) {
            throw e;
        }
    }

}
