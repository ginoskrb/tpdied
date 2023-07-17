package sql.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "camino")
public class CaminoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "sucursal_origen", referencedColumnName = "id")
	private SucursalModel sucursalOrigen;

	@ManyToOne
	@JoinColumn(name = "sucursal_destino", referencedColumnName = "id")
	private SucursalModel sucursalDestino;

	@Column(name = "tiempo_transito")
	private String tiempoTransito;

	@Column(name = "capacidad_maxima")
	private int capacidadMaxima;

	@Column(name = "estado")
	private boolean estado;

	public CaminoModel() {

	}

	public CaminoModel(SucursalModel sucursalOrigen, SucursalModel sucursalDestino, String tiempoTransito,
			int capacidadMaxima, boolean estado) {
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.tiempoTransito = tiempoTransito;
		this.capacidadMaxima = capacidadMaxima;
		this.estado = estado;
	}

	// GETTER Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SucursalModel getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(SucursalModel sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public SucursalModel getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(SucursalModel sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public String getTiempoTransito() {
		return tiempoTransito;
	}

	public void setTiempoTransito(String tiempoTransito) {
		this.tiempoTransito = tiempoTransito;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public boolean getEstado() {
		return estado;
	}

}
