package es.cic.curso.curso03.ejercicio016.repository;

import es.cic.curso.curso03.ejercicio016.exception.MyException;

public interface GestionTaquillaRepository {

	public void vendeEntrada(int numEntradas,int sala, int sesion) throws MyException;
	
	public boolean hayEntradas(int numeroEntradas,int sala, int sesion) throws MyException;
}
