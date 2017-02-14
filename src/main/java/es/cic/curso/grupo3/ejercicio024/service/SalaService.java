package es.cic.curso.grupo3.ejercicio024.service;

public interface SalaService {

	void cerrarSesion(int sesionId);

	void abrirSesion(int numSesion);

	void cerrarCine();

	void abrirCine();

	boolean esCerradoSesion(int numSesion);

	boolean hayAsientosLibres(int numSesion);
}