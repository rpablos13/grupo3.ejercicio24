package es.cic.curso.curso03.ejercicio016.dto;

import java.util.ArrayList;
import java.util.List;

public class Taquilla {

	private List<Sala> listaSalas;
	
	public Taquilla(){
		listaSalas = new ArrayList<>();
	}

	public List<Sala> getListaSalas() {
		return listaSalas;
	}

	public void setListaSalas(List<Sala> listaSalas) {
		this.listaSalas = listaSalas;
	}


	
	
}
