package es.cic.curso.curso03.ejercicio014.dominio;

import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.assertNull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso03.ejercicio014.repository.IRepository;
import es.cic.curso.curso03.ejercicio014.repository.Identificable;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public abstract class AbstractRepositoryImplTest<K extends Number, T extends Identificable<K>> {



	@PersistenceContext
	protected EntityManager em;

	public AbstractRepositoryImplTest() {
		super();
	}

	public abstract IRepository<K, T> getRepository();
	public abstract T getInstanceDeTParaNuevo();


	@Test
	public void testAdd() {
		T badulake = getInstanceDeTParaNuevo();
		assertNull(badulake.getId());
		
		badulake = getRepository().add(badulake);
		
		assertNotNull(badulake.getId());
	}
	



}