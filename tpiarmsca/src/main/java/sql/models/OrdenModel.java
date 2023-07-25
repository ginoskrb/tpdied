package sql.models; //OrdenModel

import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import sql.models.DetalleOrdenModel;

@Entity
@Table(name = "orden_provision")
public class OrdenModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@Column(name = "fecha_orden")
	private Timestamp fechaOrden;

	@ManyToOne
	@JoinColumn(name = "id_sucursal_destino", referencedColumnName = "id")
	private SucursalModel SucursalDestino;

	@Column(name = "tiempo_maximo_horas")
	private int tiempoMaximo;

	@Column(name = "estado")
	String estadoOrden;

	@OneToMany(mappedBy = "ordenProvision", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleOrdenModel> detalles = new ArrayList<DetalleOrdenModel>();

	public OrdenModel() {

	}

	public OrdenModel(Timestamp fechaOrden, SucursalModel sucursalDestino, int tiempoMaximo, String estadoOrden) {
		this.fechaOrden = fechaOrden;
		SucursalDestino = sucursalDestino;
		this.tiempoMaximo = tiempoMaximo;
		this.estadoOrden = estadoOrden;
		this.detalles = new ArrayList<DetalleOrdenModel>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaOrden() {

		
		LocalDate localDate = this.fechaOrden.toLocalDateTime().toLocalDate();

		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		
		return localDate.format(formatter);
	}

	public void setFechaOrden(Timestamp fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public SucursalModel getSucursalDestino() {
		return SucursalDestino;
	}

	public void setSucursalDestino(SucursalModel sucursalDestino) {
		SucursalDestino = sucursalDestino;
	}

	public int getTiempoMaximo() {
		return tiempoMaximo;
	}

	public void setTiempoMaximo(int tiempoMaximo) {
		this.tiempoMaximo = tiempoMaximo;
	}

	public String getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public void addDetalle(DetalleOrdenModel detalle) {
		detalles.add(detalle);
		detalle.setOrdenProvision(this);
	}

	public void removeDetalle(DetalleOrdenModel detalle) {
		detalles.remove(detalle);
		detalle.setOrdenProvision(null);
	}

	public List<DetalleOrdenModel> getDetalles() {
		return detalles;
	}
	

}
