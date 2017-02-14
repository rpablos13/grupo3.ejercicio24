package es.cic.curso.grupo3.ejercicio024.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.grupo3.ejercicio024.repository.Identificable;

@Entity
@Table(name="sala")
public class Sala implements Identificable<Long> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="capacidad")
	private int capacidad;
	
	@Column(name="asientosocupados")
	private int asientosOcupados;
	
	@Column(name="numSesion")
	private int numSesion;
	
	@Column(name="cerrado")
	private boolean cerrado;
	
	@OneToMany(mappedBy="salaId") 
	private List<Venta> listaVentas = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getAsientosOcupados() {
		return asientosOcupados;
	}

	public void setAsientosOcupados(int asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}

	public int getNumSesion() {
		return numSesion;
	}

	public void setNumSesion(int numSesion) {
		this.numSesion = numSesion;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}
}