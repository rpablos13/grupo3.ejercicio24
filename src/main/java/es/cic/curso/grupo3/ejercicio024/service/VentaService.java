package es.cic.curso.grupo3.ejercicio024.service;

import java.util.Collection;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.dominio.Venta;

public interface VentaService {

	double obtenerRecaudacionCine();

	double obtenerRecaudacionSala(Sala sala);

	Venta venderEntrada(Sala sala, int numSesion, int cantidad);

	Collection<Venta> getVentas();

	void borrarVenta(Venta venta);

	Venta cambiarVenta(long id, long salaId, int numSesion, int cantidad);
}