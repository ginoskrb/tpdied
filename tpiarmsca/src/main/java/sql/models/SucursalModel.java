package sql.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "sucursal")
public class SucursalModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="hapertura")
	private String hapertura;
	
	@Column(name="hcierre")
	private String hcierre;
	
	@Column(name="estado")
	private boolean estado;
	
	public SucursalModel() {}
	
	public SucursalModel(String nombre, String hapertura, String hcierre, boolean estado) {
		this.nombre = nombre;
		this.hapertura = hapertura;
		this.hcierre = hcierre;
		this.estado = estado;
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
	public String getHapertura() {
		return hapertura;
	}
	public void setHapertura(String hapertura) {
		this.hapertura = hapertura;
	}
	public String getHcierre() {
		return hcierre;
	}
	public void setHcierre(String hcierre) {
		this.hcierre = hcierre;
	}
	public String isEstado() {
		if(estado) {
			return "OPERATIVO";
		}else return "NO OPERATIVO";
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SucursalModel other = (SucursalModel) obj;
		return id == other.id;
	}
	
	
}
