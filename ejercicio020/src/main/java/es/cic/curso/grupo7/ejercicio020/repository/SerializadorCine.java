package es.cic.curso.grupo7.ejercicio020.repository;

import es.cic.curso.grupo7.ejercicio020.dto.Sala;
import es.cic.curso.grupo7.ejercicio020.dto.Sesion;

public class SerializadorCine {

	
	public String serializarSala(Sala s, int indiceSesion){
		int aforo = s.getAforo();
		double rec = s.getRecaudacion();
		String sesion = serializarSesion(s.getSesiones().get(indiceSesion));
		
		return s.getNumeroSala()+","+aforo+","+rec+","+sesion;
		
	}
	public String serializarSesion(Sesion s){
		int ocupados = s.getAforoOcupado();
		int libres = s.getAforoLibre();
		return s.getNumeroSesion()+","+ocupados+","+libres;
		
		
	}
}
