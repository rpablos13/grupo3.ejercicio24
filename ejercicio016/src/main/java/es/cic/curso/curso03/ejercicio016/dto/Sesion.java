package es.cic.curso.curso03.ejercicio016.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SESION")
public class Sesion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(name="aforoLibre")
	private int aforoLibre;
	
	@Column(name="aforoOcupado")
	private int aforoOcupado;

	
	@Column(name="numeroSesion")
	private int numeroSesion;
	
	@Column(name="numeroSala")
	private int numeroSala;
	
	@Transient
	private boolean cerrado;
	
	
	//Getters and Setterss
	
	public int getAforoOcupado() {
		return aforoOcupado;
	}
	public void setAforoOcupado(int aforoOcupado) {
		this.aforoOcupado = aforoOcupado;
	}
	public int getAforoLibre() {
		return aforoLibre;
	}
	public void setAforoLibre(int aforoLibre) {
		this.aforoLibre = aforoLibre;
	}
	public void vendeEntrada(int x){
		aforoLibre -= x;
		aforoOcupado += x;
	}

	public int getNumeroSesion() {
		return numeroSesion;
	}

	public void setNumeroSesion(int numeroSesion) {
		this.numeroSesion = numeroSesion;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
