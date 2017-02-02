package es.cic.curso.grupo7.ejercicio020.repository;

import java.util.List;

import es.cic.curso.grupo7.ejercicio020.dto.Sala;

public interface SalaRepository extends IRepository<Long, Sala> {

	List<Sala> listJPQL();

	
}
