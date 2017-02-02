package es.cic.curso.curso03.ejercicio014.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso03.ejercicio014.repository.Identificable;

@Entity
@Table(name="INVENTARIO")
public class Badulake implements Identificable<Long>{


	private static final long serialVersionUID = -3399065408815330007L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="producto")
	private String producto;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="lugar")
	private String lugar;
	
	public Badulake () {
		super();
	}
	
	
	public Badulake(String producto, double precio, int cantidad, String lugar) {
		super();
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.lugar = lugar;
	}



	public Badulake(Long id, String producto, double precio, int cantidad, String lugar) {
		
		super();
		this.id = id;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.lugar = lugar;
	}


	
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	

	@Override
	public String toString() {
		return "Badulake [id=" + id + ", producto=" + producto + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", lugar=" + lugar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Badulake other = (Badulake) obj;
		if (cantidad != other.cantidad)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	



	
}
