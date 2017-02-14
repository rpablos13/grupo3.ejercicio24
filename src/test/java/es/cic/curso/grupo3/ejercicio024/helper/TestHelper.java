package es.cic.curso.grupo3.ejercicio024.helper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;

@Repository
public class TestHelper {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long generaVentaSesionUno(Sala sala) {
		Venta v = new Venta();
		v.setSalaId(sala);
		v.setNumSesion(sala.getNumSesion());
		v.setCantidad(10);
		v.setBeneficio(50);
		
		em.persist(v);
		return v.getId();
	}
	
	public Long generaVentaSesionDos(Sala sala) {
		Venta v = new Venta();
		v.setSalaId(sala);
		v.setNumSesion(sala.getNumSesion());
		v.setCantidad(10);
		v.setBeneficio(50);
		
		em.persist(v);
		return v.getId();
	}
	
	public Long generaSalaSesionUno(){
		Sala s = new Sala();
		s.setNumSesion(1);
		s.setCapacidad(100);
		s.setAsientosOcupados(50);
		s.setCerrado(false);
		
		em.persist(s);
		return s.getId();
	}
	
	public Long generaSalaSesionDos(){
		Sala s = new Sala();
		s.setNumSesion(2);
		s.setCapacidad(100);
		s.setAsientosOcupados(50);
		s.setCerrado(false);
		
		em.persist(s);
		return s.getId();
	}
}