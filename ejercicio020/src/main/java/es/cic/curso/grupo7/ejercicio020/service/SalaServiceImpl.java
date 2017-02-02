package es.cic.curso.grupo7.ejercicio020.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso.grupo7.ejercicio019.dominio.Notas;
import es.cic.curso.grupo7.ejercicio019.dominio.NotesException;
import es.cic.curso.grupo7.ejercicio019.repositorio.NotasRepository;
import es.cic.curso.grupo7.ejercicio020.repository.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService {

	@Autowired
	private SalaRepository salaRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
		
	@Override
	public void aniadirSesion(String texto, int idUsuario) {
		insertarNota(DEFAULT, SINURL, texto, idUsuario);
	}

	@Override
	public void insertarNota(boolean insertURL, String uRL, String texto, int idUsuario) {
		Notas nota = new Notas(uRL, texto, insertURL, idUsuario);
		notasRep.add(nota);
	}
	
	@Override
	public void modificarTexto(String texto, int idUsuario, Long id) {
		comprobarExistencia(id);
		
		Notas notaExistente = notasRep.read(id);
		notaExistente.setDatos(texto);
		
		notasRep.update(notaExistente);
	}

	@Override
	public void modificarURL(boolean insertarURL, String uRL, int idUsuario, Long id) {
		
		comprobarExistencia(id);
		
		if (insertarURL) {

			Notas notaExistente = notasRep.read(id);
			notaExistente.setuRL(uRL);

			notaExistente.setInsertURL(true);
			
			notasRep.update(notaExistente);
			
		} else {
			
			Notas notaExistente = notasRep.read(id);
			notaExistente.setuRL(SINURL);
			notaExistente.setInsertURL(DEFAULT);
			
			notasRep.update(notaExistente);
		}
	}
	
	@Override
	public void realizado(boolean hecho,int idUsuario, Long id) {
		comprobarExistencia(id);
		Notas notaExistente = notasRep.read(id);
		if (hecho) {

			notaExistente.setHecho(hecho);
		} else {
			throw new NotesException("La nota "+ id + " no ha sido realizada " + id);
		}
		
	}
	
	private void comprobarExistencia(Long id) {
		if (notasRep.read(id) == null) {
			throw new NotesException("No existe la nota " + id);
		}
	}
}
