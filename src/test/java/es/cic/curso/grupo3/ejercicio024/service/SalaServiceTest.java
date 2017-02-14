package es.cic.curso.grupo3.ejercicio024.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class SalaServiceTest {

	@Autowired
	private SalaService salaService;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private TestHelper tHelper;

	Long claveSala;
	Sala sala;
	
	@Before
	public void setUp() throws Exception {
		claveSala = tHelper.generaSalaSesionUno();
		sala = salaRepository.read(claveSala);
	}

	@Test
	public void testEsCerradoSesion(){
		boolean resultado;
		
		resultado = salaService.esCerradoSesion(sala.getNumSesion());
		
		assertEquals(false, resultado);
	}
	
	@Test
	public void testCerrarSesion(){
		salaService.cerrarSesion(sala.getNumSesion());
		
		boolean resultado = salaService.esCerradoSesion(sala.getNumSesion());
		
		assertEquals(true, resultado);
	}
	
	@Test
	public void testHayAsientosLibres(){
		boolean resultado;
		
		resultado = salaService.hayAsientosLibres(sala.getNumSesion());
		
		assertEquals(true, resultado);
	}
}