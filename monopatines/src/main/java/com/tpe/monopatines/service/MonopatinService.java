package com.tpe.monopatines.service;

import com.tpe.monopatines.DTO.DTOMonopatinCantViajes;
import com.tpe.monopatines.DTO.DTOMonopatin;
import com.tpe.monopatines.DTO.DTOReportePorTiempo;
import com.tpe.monopatines.model.Monopatin;
import com.tpe.monopatines.repository.MonopatinRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonopatinService {
    
    @Autowired
    private MonopatinRepository repositorio;

    @Transactional
    public Monopatin guardar(Monopatin m) throws Exception {
        try{
            return this.repositorio.save(m);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional
    public DTOMonopatin actualizar(Long id, Monopatin nuevo) throws Exception {
        try{
            if(this.repositorio.existsById(id)) {
                Monopatin antiguo = this.repositorio.findById(id).get();
                antiguo.setEstado(nuevo.getEstado());
                antiguo.setLatitud(nuevo.getLatitud());
                antiguo.setLongitud(nuevo.getLongitud());
                antiguo.setKmsRecorridos(nuevo.getKmsRecorridos());
                this.repositorio.save(antiguo);

                return new DTOMonopatin(antiguo);
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw e;
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
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DTOMonopatin obtenerPorId(Long id) throws Exception {
        try{
            return new DTOMonopatin(this.repositorio.findById(id).get());
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOMonopatin> obtenerDisponiblesEnZona(double latitud, double longitud) throws Exception {
        try{
            double rangoBusqueda = 30;
            double latitudMin = latitud - rangoBusqueda;
            double latitudMax = latitud + rangoBusqueda;
            double longitudMin =  longitud - rangoBusqueda;
            double longitudMax =  longitud + rangoBusqueda;

            return this.repositorio.obtenerDisponiblesEnZona(latitudMin, latitudMax, longitudMin, longitudMax);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public String obtenerReporteMantenimientoVsOperando() throws Exception {
        try{
            int cantMant = repositorio.obtenerCantEnEstado("en mantenimiento");
            int cantOper = repositorio.obtenerCantEnEstado("en uso");
            cantOper += repositorio.obtenerCantEnEstado("disponible");

            return "Monopatines en operacion: " + cantOper + ", Monopatines en mantenimiento: " + cantMant;
        }catch(Exception e) {
            throw  e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOMonopatinCantViajes> obtenerConViajesMayoresEnAnio(Long cantViajes, int anio) {
        try{
            return  this.repositorio.obtenerConViajesMayoresEnAnio(cantViajes, anio);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOReportePorTiempo> obtenerReporteConTiempoPausa() {
        try{
            return this.repositorio.obtenerReporteConTiempoPausa();
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOReportePorTiempo> obtenerReporteSinTiempoPausa() {
        try{
            return this.repositorio.obtenerReporteSinTiempoPausa();
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<DTOMonopatin> obtenerConKmsEntre(double minKms, double maxKms) {
        try{
            return this.repositorio.obtenerConKmsEntre(minKms, maxKms);
        }catch(Exception e){
            throw e;
        }
    }

}
