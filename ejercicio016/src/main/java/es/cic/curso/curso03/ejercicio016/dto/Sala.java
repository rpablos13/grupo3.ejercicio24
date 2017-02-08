package es.cic.curso.curso03.ejercicio016.dto;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SALA")
public class Sala {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="aforo")
	private int aforo;
	
	@Column(name="precioEntrada")
	private static final double PRECIO_ENTRADA = 5;
	
	@Column(name="recaudacion")
	private double recaudacion=0;
	
	@Column(name="numeroSala")
	private int numeroSala;

	@Transient
	private boolean cerrado;
	
	@Transient
	private List<Sesion> sesiones;
	
	
	
	//Getters and Setters
	
	public Sala(){
		sesiones = new ArrayList<>();
		cerrado = false;
	}
	
	public int getAforo() {
		return aforo;
	}
	public void setAforoOcupado(int aforo) {
		this.aforo = aforo;
	}
	public double getPrecioEntrada() {
		return PRECIO_ENTRADA;
	}

	public double getRecaudacion() {
		return recaudacion;
	}

	public void sumaRecaudacion(double recaudacion) {
		this.recaudacion += recaudacion;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	public int obtenLibres(int sesion){
		return sesiones.get(sesion).getAforoLibre();
	}
	public int obtenOcupadas(int sesion){
		return sesiones.get(sesion).getAforoOcupado();
		
	}

	public void setRecaudacion(double recaudacion) {
		this.recaudacion = recaudacion;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
