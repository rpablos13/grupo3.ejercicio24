package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

import es.cic.curso.curso03.ejercicio016.dto.Sesion;

public interface SesionRepository extends IRepository<Long, Sesion>{

	List<Sesion> listJPQL();
}
