package es.cic.curso.curso03.ejercicio016.repository;

import java.util.List;

public interface IRepository<K, T> {
	T add(T nuevo);
	T read(K id);
	List<T> list();	
	T update(T modificado);
	void delete(K id);
}
