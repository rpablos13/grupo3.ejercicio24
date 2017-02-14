package es.cic.curso.grupo3.ejercicio024.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.curso.grupo3.ejercicio024.repository.Identificable;

@Entity
@Table(name="venta")
public class Venta implements Identificable<Long> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name="salaId")
	@ManyToOne(fetch=FetchType.LAZY)
	private Sala salaId;
	
	@Column(name="numSesion")
	private int numSesion;
	
	@Column(name="precio")
	private static double precio = 5.0;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="beneficio")
	private double beneficio;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Sala getSalaId() {
		return salaId;
	}

	public void setSalaId(Sala salaId) {
		this.salaId = salaId;
	}

	public int getNumSesion() {
		return numSesion;
	}

	public void setNumSesion(int numSesion) {
		this.numSesion = numSesion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	public static double getPrecio() {
		return precio;
	}
}