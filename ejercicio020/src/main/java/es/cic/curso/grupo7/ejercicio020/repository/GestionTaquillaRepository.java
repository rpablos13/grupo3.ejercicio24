package es.cic.curso.grupo7.ejercicio020.repository;

import es.cic.curso.grupo7.ejercicio020.exception.MyException;

public interface GestionTaquillaRepository {

	
	public void vendeEntrada(int numEntradas,int sala, int sesion) throws MyException;
	
	public boolean hayEntradas(int numeroEntradas,int sala, int sesion) throws MyException;
}
