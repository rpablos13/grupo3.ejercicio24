package es.cic.curso.grupo3.ejercicio024.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

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
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.helper.TestHelper;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;
import es.cic.curso.grupo3.ejercicio024.repository.VentaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={
				"classpath:es/cic/curso/grupo3/ejercicio024/applicationContext.xml"
		})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class})
@Transactional
public class VentaServiceTest {
	
	@Autowired
	private VentaService ventaService;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private TestHelper tHelper;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private SalaService salaService;

	Long claveVenta;
	Long claveSala;
	Sala sala;
	
	@Before
	public void setUp() throws Exception {
		claveSala = tHelper.generaSalaSesionUno();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionUno(sala);
	}
	
	@Test
	public void testObtenerRecaudacionSala(){
		claveSala = tHelper.generaSalaSesionDos();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionDos(sala);
		
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);
		
		double recaudacion;
		
		recaudacion = ventaService.obtenerRecaudacionSala(sala);
		assertEquals(150.0, recaudacion, 0.1);
	}	
	
	@Test
	public void testObtenerRecaudacionCine(){
		claveSala = tHelper.generaSalaSesionDos();
		sala = salaRepository.read(claveSala);
		claveVenta = tHelper.generaVentaSesionDos(sala);
		
		tHelper.generaVentaSesionUno(sala);
		tHelper.generaVentaSesionUno(sala);
		
		double recaudacion;
		
		recaudacion = ventaService.obtenerRecaudacionCine();
		assertEquals(200.0, recaudacion, 0.1);
	}	
	
	@Test
	public void testVenderEntrada_ConDescuento(){
		Venta venta = ventaService.venderEntrada(sala, 1, 5);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		assertEquals(22.5, ganancia, 0.001);
		assertEquals(55, asientosOcupados);
	}
	
	@Test
	public void testVenderEntrada_SinDescuento_SesionAbierta(){
		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(20.0, ganancia, 0.001);
		assertEquals(54, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada(){
		salaService.cerrarSesion(1);
		
		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada_SesionAbierta(){
		salaService.cerrarSesion(1);

		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirSesion(1);
		
		venta = ventaService.venderEntrada(sala, 1, 4);
		
		ganancia = venta.getBeneficio();
		asientosOcupados = sala.getAsientosOcupados();
		resultado = salaService.esCerradoSesion(1);
		
		assertEquals(20.0, ganancia, 0.001);
		assertEquals(54, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado(){
		salaService.cerrarCine();

		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado_CineAbierto(){
		salaService.cerrarCine();

		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirCine();
		
		venta = ventaService.venderEntrada(sala, 1, 4);
		
		ganancia = venta.getBeneficio();
		asientosOcupados = sala.getAsientosOcupados();
		resultado = salaService.esCerradoSesion(1);
		
		assertEquals(20.0, ganancia, 0.001);
		assertEquals(54, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_SesionCerrada_CineAbierto(){
		salaService.cerrarSesion(1);

		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirCine();
		
		venta = ventaService.venderEntrada(sala, 1, 4);
		
		ganancia = venta.getBeneficio();
		asientosOcupados = sala.getAsientosOcupados();
		resultado = salaService.esCerradoSesion(1);
		
		assertEquals(20.0, ganancia, 0.001);
		assertEquals(54, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testVenderEntrada_CineCerrado_SesionAbierta(){
		salaService.cerrarCine();

		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		
		double ganancia = venta.getBeneficio();
		int asientosOcupados = sala.getAsientosOcupados();
		boolean resultado = salaService.esCerradoSesion(1);
		
		assertEquals(0, ganancia, 0.001);
		assertEquals(50, asientosOcupados);
		assertEquals(true, resultado);
		
		salaService.abrirSesion(1);
		
		venta = ventaService.venderEntrada(sala, 1, 4);
		
		ganancia = venta.getBeneficio();
		asientosOcupados = sala.getAsientosOcupados();
		resultado = salaService.esCerradoSesion(1);
		
		assertEquals(20.0, ganancia, 0.001);
		assertEquals(54, asientosOcupados);
		assertEquals(false, resultado);
	}
	
	@Test
	public void testBorrarVenta(){
		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		ventaRepository.add(venta);
		Collection<Venta> listaVentas = ventaService.getVentas();
		
		assertEquals(2, listaVentas.size());
		assertEquals(54, sala.getAsientosOcupados());
		
		ventaService.borrarVenta(venta);
		
		listaVentas = ventaService.getVentas();
		
		assertEquals(1, listaVentas.size());
		assertEquals(50, sala.getAsientosOcupados());
	}
	
	@Test
	public void testCambiarVenta(){
		Venta venta = ventaService.venderEntrada(sala, 1, 4);
		ventaRepository.add(venta);
		Collection<Venta> listaVentas = ventaService.getVentas();
		
		assertEquals(2, listaVentas.size());
		assertEquals(54, sala.getAsientosOcupados());
		assertEquals(20, venta.getBeneficio(), 0.001);
		
		venta = ventaService.cambiarVenta(venta.getId(), venta.getSalaId().getId(), venta.getNumSesion(), 3);
		
		listaVentas = ventaService.getVentas();
		
		assertEquals(2, listaVentas.size());
		assertEquals(53, sala.getAsientosOcupados());
		assertEquals(15, venta.getBeneficio(), 0.001);
	}
}