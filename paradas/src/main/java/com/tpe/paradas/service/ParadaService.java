package com.tpe.paradas.service;

import com.tpe.paradas.DTO.DTOParada;
import com.tpe.paradas.model.Parada;
import com.tpe.paradas.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository repositorio;

    @Transactional
    public Parada guardar(Parada p) throws Exception{
        try{
            return this.repositorio.save(p);
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DTOParada actualizar(Long id, DTOParada p) throws Exception {
        try{
            if(this.repositorio.existsById(id)){
                Parada antigua = this.repositorio.findById(id).get();
                antigua.setLongitud(p.getLongitud());
                antigua.setLatitud(p.getLatitud());
                this.repositorio.save(antigua);

                return new DTOParada(antigua);
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean eliminar(Long id) throws Exception {
        try{
            if(this.repositorio.existsById(id)) {
                this.repositorio.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DTOParada obtenerPorId(Long id) throws Exception{
        try{
            return new DTOParada(this.repositorio.findById(id).orElseThrow());
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
