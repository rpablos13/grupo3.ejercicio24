package es.cic.curso.grupo7.ejercicio020.service;

public interface SalaService {

	boolean DEFAULT = false;
	String SINURL = null;
	void realizado(boolean hecho, int idUsuario, Long id);
	void modificarURL(boolean insertarURL, String uRL, int idUsuario, Long id);
	void modificarTexto(String texto, int idUsuario, Long id);
	void aniadirSala(int numeroSala);
	void insertarNota(String texto, int idUsuario);

}