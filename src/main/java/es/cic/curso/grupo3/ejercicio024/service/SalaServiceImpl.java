package es.cic.curso.grupo3.ejercicio024.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.grupo3.ejercicio024.dominio.Sala;
import es.cic.curso.grupo3.ejercicio024.repository.SalaRepository;

@Service
@Transactional
public class SalaServiceImpl implements SalaService {
	
	@Autowired 
	private SalaRepository salaRepository;
	
	@Override
	public boolean hayAsientosLibres(int numSesion){
		boolean resultado = false;

		if (!esCerradoSesion(numSesion)){
			for (Sala s :salaRepository.list()){
				if (s.getNumSesion() == numSesion){
					int asientosOcupados = s.getAsientosOcupados();
					int asientosTotales = s.getCapacidad();

					resultado = hayAsientos(asientosOcupados, asientosTotales);
				}
			}
		}
		return resultado;
	}

	private boolean hayAsientos(int asientosOcupados, int asientosTotales) {
		return asientosOcupados < asientosTotales;
	}
	
	@Override
	public void cerrarSesion(int numSesion){
		for (Sala s : salaRepository.list()){
			if (s.getNumSesion() == numSesion){
				s.setCerrado(true);
			}
		}
	}
	
	@Override
	public void abrirSesion(int numSesion){
		for (Sala s : salaRepository.list()){
			if (s.getNumSesion() == numSesion){
				s.setCerrado(false);
			}
		}
	}

	@Override
	public void cerrarCine() {
		for (Sala s : salaRepository.list()){
			s.setCerrado(true);
		}
	}

	@Override
	public void abrirCine() {
		for (Sala s : salaRepository.list()){
			s.setCerrado(false);
		}
	}
	
	@Override
	public boolean esCerradoSesion(int numSesion) {
		boolean resultado = false;		

		for (Sala s : salaRepository.list()){
			if (s.isCerrado() && s.getNumSesion() == numSesion){
				resultado = true;
			}
		}
		return resultado;
	}
}