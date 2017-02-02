package es.cic.curso.curso03.ejercicio014.repository;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso03.ejercicio014.dominio.Badulake;
import es.cic.curso.curso03.ejercicio014.repository.AbstractRepositoryImpl;
import es.cic.curso.curso03.ejercicio014.repository.BadulakeRepository;

@Repository
@Transactional
public class BadulakeRepositoryImpl extends AbstractRepositoryImpl<Long, Badulake> implements BadulakeRepository {

	@Override
	public Class<Badulake> getClassDeT() {
		return Badulake.class;
	}


	@Override
	public String getNombreTabla() {
		return "inventario";
	}	
}
