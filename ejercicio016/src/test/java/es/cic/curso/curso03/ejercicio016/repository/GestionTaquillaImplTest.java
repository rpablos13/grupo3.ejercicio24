package es.cic.curso.curso03.ejercicio016.repository;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso.curso03.ejercicio016.dto.Taquilla;
import es.cic.curso.curso03.ejercicio016.exception.MyException;
import es.cic.curso.curso03.ejercicio016.dto.Sala;
import es.cic.curso.curso03.ejercicio016.dto.Sesion;
import es.cic.curso.curso03.ejercicio016.repository.LecturaEscrituraFicheros;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/curso03/ejercicio016/applicationContext.xml"
				})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class GestionTaquillaImplTest {

	public static final String CAPITOL = "Capitol";

	@Autowired
	private LecturaEscrituraFicheros sut;

	
	
	
	
	@PersistenceContext
	private EntityManager em;

	private Taquilla taquilla;
	private Long clavePrimariaSala;
	private Long clavePrimariaSesion;
	
	
	@Before
	public void setUp() throws Exception {
		
		sut.leeFichero();
		
		
		Sala sala = new Sala();
		sala.setAforo(300);
		sala.setAforoOcupado(200);
		sala.setNumeroSala(1);
		sala.setRecaudacion(325.23);
		em.persist(sala);
		clavePrimariaSala = sala.getId();
		
		Sesion sesion = new Sesion();
		sesion.setAforoLibre(100);
		sesion.setAforoOcupado(200);
		sesion.setNumeroSesion(1);
		sesion.setNumeroSala(1);
		em.persist(sesion);
		clavePrimariaSesion = sesion.getId();
	}


		@Test
		public void testLeerSala() throws IOException {
		
			taquilla = sut.leeFichero();
			assertNotNull(taquilla.getListaSalas());
		}
		

}
//
//	@Test
//	public void testRead() {
//		Long clavePrimaria = generaCineLectura();
//		
//		Cine resultado = sut.read(clavePrimaria);
//		
//		assertEquals(CAPITOL,  resultado.getNombre());
//	}
//
//	@Test(expected=PersistenceException.class)
//	public void testRead_NoExiste() {
//		Long clavePrimaria = Long.MIN_VALUE;
//		
//		Cine resultado = sut.read(clavePrimaria);
//		
//		assertEquals(CAPITOL,  resultado.getNombre());
//	}	
//	
//	@Test
//	public void testList() {
//		generaCineLectura();
//		generaCineLectura();
//		generaCineLectura();
//		
//		List<Cine> resultado = sut.list();
//		
//		assertTrue(resultado.size() >= 3);
//	}	
//	
//	@Test
//	public void testListJPQL() {
//		generaCineLectura();
//		generaCineLectura();
//		generaCineLectura();
//		
//		List<Cine> resultado = sut.listJPQL();
//		
//		assertTrue(resultado.size() >= 3);
//	}		
//	
//	@Test
//	public void testUpdate() {
//		Long clavePrimaria = generaCineLectura();
//		
//		Cine cine2 = new Cine();
//		cine2.setId(clavePrimaria);
//		cine2.setNombre("Otro");
//		
//		Cine resultado = sut.update(cine2);
//
//		Cine enBBDD = em.find(Cine.class, clavePrimaria);		
//		assertEquals("Otro", enBBDD.getNombre());
//		assertEquals("Otro", resultado.getNombre());
//	}		
//	
//	@Test
//	public void testDelete() {
//		Long clavePrimaria = generaCineLectura();
//		
//		sut.delete(clavePrimaria);
//		Cine c;
//		try {
//			c = em.find(Cine.class, clavePrimaria);
//		} catch (PersistenceException pe) {
//			return;
//		}
//		assertNull(c);
//	}	
//	
//	private Long generaCineLectura() {
//		Cine cine = new Cine();
//		cine.setNombre(CAPITOL);
//		
//		em.persist(cine);
//	
//		return cine.getId();
//	}	

