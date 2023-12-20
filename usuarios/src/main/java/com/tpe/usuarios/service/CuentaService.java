package com.tpe.usuarios.service;

import com.tpe.usuarios.DTO.DTOCuenta;
import com.tpe.usuarios.model.Cuenta;
import com.tpe.usuarios.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaService {
    
    @Autowired
    private CuentaRepository repository;

    @Transactional
    public Cuenta guardar(Cuenta c) throws Exception {
        try{
            return this.repository.save(c);
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional
    public DTOCuenta actualizar(Long id, DTOCuenta nueva) throws Exception {
        try{
            if(this.repository.existsById(id)) {
                Cuenta antigua = this.repository.findById(id).get();
                antigua.setSaldo(nueva.getSaldo());
                antigua.setEstado(nueva.getEstado());
                this.repository.save(antigua);

                return new DTOCuenta(antigua);
            }else{
                throw new Exception();
            }
        }catch(Exception e) {
            throw e;
        }
    }

    @Transactional
    public boolean anularCuenta(Long id) throws Exception{
        try{
            if(this.repository.existsById(id)){
                Cuenta anulada = this.repository.findById(id).get();
                anulada.setEstado("inhabilitada");
                this.repository.save(anulada);

                return true;
            }else {
                throw new Exception();
            }
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DTOCuenta obtenerPorId(Long id) throws Exception {
        try {
            return new DTOCuenta(this.repository.findById(id).get());
        }catch(Exception e) {
            throw e;
        }
    }

}
