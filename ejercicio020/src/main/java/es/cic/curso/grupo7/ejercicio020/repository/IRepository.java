package es.cic.curso.grupo7.ejercicio020.repository;

import java.util.List;

public interface IRepository<K, T> {
	T add(T nuevo);
	T read(K id);
	List<T> list();	
	T update(T modificado);
	void delete(K id);
	
}
