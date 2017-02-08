package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import es.cic.curso.curso03.ejercicio016.dto.Sala;

@Repository
@Transactional
public class SalaRepositoryImpl implements SalaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Sala add(Sala nuevo) {
		entityManager.persist(nuevo);
		return nuevo;
	}

	@Override
	public Sala read(Long id) {
		Sala resultado = entityManager.find(Sala.class, id);
		return resultado;
	}

	@Override
	public List<Sala> list() {
		return entityManager
				.createNativeQuery("select id, nombre from SALA", Sala.class)
				.getResultList();
	}

	@Override
	public List<Sala> listJPQL() {
		return entityManager
				.createQuery("select c from SALA c", Sala.class)
				.getResultList();
	}

	@Override
	public void delete(Long id) {
		Sala sala = new Sala();
		sala.setId(id);
		delete(sala);
	}	
	

	private void delete(Sala sala) {
		sala = read(sala.getId());
		entityManager.remove(sala);
	}

	@Override
	public Sala update(Sala modificado) {
//		Cine cine = read(modificado.getId());
//		cine.setNombre(modificado.getNombre());
		modificado = entityManager.merge(modificado);
		//entityManager.flush();
		return modificado;
	}	
	

}
