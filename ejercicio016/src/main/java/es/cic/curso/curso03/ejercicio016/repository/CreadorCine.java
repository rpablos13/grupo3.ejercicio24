package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

import es.cic.curso.curso03.ejercicio016.dto.Sesion;

public class CreadorCine {

	public Sesion creaDTO(List<String> lista){
		Sesion s = new Sesion();
		int numeroSesion = Integer.parseInt(lista.get(3));
		int aforoOcupado = Integer.parseInt(lista.get(4));
		int aforoLibre = Integer.parseInt(lista.get(5));
		s.setAforoLibre(aforoLibre);
		s.setAforoOcupado(aforoOcupado);
		s.setNumeroSesion(numeroSesion);
		return s;
	}
}
