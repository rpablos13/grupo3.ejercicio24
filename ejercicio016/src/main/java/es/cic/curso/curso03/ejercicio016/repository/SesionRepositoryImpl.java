package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso03.ejercicio016.dto.Sesion;


@Repository
@Transactional
public class SesionRepositoryImpl implements SesionRepository {


		@PersistenceContext
		private EntityManager entityManager;
		
		public Sesion add(Sesion nuevo) {
			entityManager.persist(nuevo);
			return nuevo;
		}

		@Override
		public Sesion read(Long id) {
			Sesion resultado = entityManager.find(Sesion.class, id);
			return resultado;
		}

		@Override
		public List<Sesion> list() {
			return entityManager
					.createNativeQuery("select id, nombre from SESION", Sesion.class)
					.getResultList();
		}

		@Override
		public List<Sesion> listJPQL() {
			return entityManager
					.createQuery("select c from SESION c", Sesion.class)
					.getResultList();
		}

		@Override
		public void delete(Long id) {
			Sesion sesion = new Sesion();
			sesion.setId(id);
			delete(sesion);
		}	
		

		private void delete(Sesion sesion) {
			sesion = read(sesion.getId());
			entityManager.remove(sesion);
		}

		@Override
		public Sesion update(Sesion modificado) {
//			Cine cine = read(modificado.getId());
//			cine.setNombre(modificado.getNombre());
			modificado = entityManager.merge(modificado);
			//entityManager.flush();
			return modificado;
		}	
		
	
}
