package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

import es.cic.curso.curso03.ejercicio016.dto.Sala;

public interface SalaRepository extends IRepository<Long, Sala> {

	List<Sala> listJPQL();

}
