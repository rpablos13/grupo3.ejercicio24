package es.cic.curso.grupo7.ejercicio020.repository;

import java.util.List;

import es.cic.curso.grupo7.ejercicio020.dto.Sesion;

public interface SesionRepository extends IRepository<Long, Sesion>{

	List<Sesion> listJPQL();
	
}
