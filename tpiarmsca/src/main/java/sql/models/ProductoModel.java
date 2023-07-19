package sql.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "producto")
public class ProductoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio_unitario")
	private float precioUnitario;
	
	@Column(name="peso_kg")
	private float pesoKg;
	
	public ProductoModel() {}
	
	public ProductoModel(String nombre, String descripcion, float precioUnitario, float pesoKg) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.pesoKg = pesoKg;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(float pesoKg) {
		this.pesoKg = pesoKg;
	}

	@Override
	public String toString() {
		return  nombre;
	}
	
	
	
	
}
