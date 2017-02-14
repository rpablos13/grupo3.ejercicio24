package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;
import es.cic.curso.grupo3.ejercicio024.repository.VentaRepository;

@Service
@Transactional
public class VentaServiceImpl implements VentaService {
	
	@Autowired 
	private VentaRepository ventaRepository;
	@Autowired 
	private SalaRepository salaRepository;
	@Autowired 
	private SalaService salaService;
	
	public void anadirAsientosOcupados(Sala sala, int cantidad){
		sala.setAsientosOcupados(sala.getAsientosOcupados() + cantidad);
		salaRepository.update(sala);
	}
	
	@Override
	public double obtenerRecaudacionSala(Sala sala){
		double recaudacion = 0;
		
		for (Venta v : ventaRepository.list()){
			if (v.getSalaId().getId() == sala.getId()){
			recaudacion += v.getBeneficio();
			}
		}
		return recaudacion;
	}
	
	@Override
	public double obtenerRecaudacionCine(){
		double recaudacion = 0;
		
		for (Venta v : ventaRepository.list()){
			recaudacion += v.getBeneficio();
		}
		return recaudacion;
	}
	
	@Override
	public Venta venderEntrada(Sala sala, int numSesion, int cantidad){
		Venta venta = new Venta();

		if(salaService.hayAsientosLibres(numSesion)){
			if (cantidad >= 5){
				anadirAsientosOcupados(sala, cantidad);
				venta.setSalaId(sala);
				venta.setNumSesion(numSesion);
				venta.setCantidad(cantidad);
				venta.setBeneficio((cantidad * Venta.getPrecio()) * 0.9);
			} else {
				anadirAsientosOcupados(sala, cantidad);
				venta.setSalaId(sala);
				venta.setNumSesion(numSesion);
				venta.setCantidad(cantidad);
				venta.setBeneficio(cantidad * Venta.getPrecio());
			}
		}
		ventaRepository.add(venta);
		return venta;
	}
	
	@Override
	public void borrarVenta(Venta venta){
		anadirAsientosOcupados(venta.getSalaId(), -venta.getCantidad());
		
		ventaRepository.delete(venta.getId());
	}

	@Override
	public Venta cambiarVenta(long id, long salaId, int numSesion, int cantidad){
		Venta venta = ventaRepository.read(id);
		
		borrarVenta(venta);
		
		Venta nuevaVenta = venderEntrada(salaRepository.read(salaId), numSesion, cantidad);

		ventaRepository.add(nuevaVenta);
		return nuevaVenta;
	}
	
	@Override
	public Collection<Venta> getVentas() {
		return ventaRepository.list();
	}
}