package com.tpe.mantenimiento.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tpe.mantenimiento.model.Mantenimiento;

public interface MantenimientoRepository extends MongoRepository<Mantenimiento, String> {

	@Query("{'idMonopatin': ?0, 'estaReparado': ?1}")
	public Mantenimiento buscarPorIdMonopatinYReparado(Long idMonopatin, boolean estaReparado);

}
