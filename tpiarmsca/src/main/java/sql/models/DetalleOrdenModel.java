package sql.models; // DetallerOrdenModel

import javax.persistence.*;

@Entity
@Table(name = "detalle_orden_provision")
public class DetalleOrdenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "orden_provision_id")
    private OrdenModel ordenProvision;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "cantidad")
    private int cantidad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdenModel getOrdenProvision() {
		return ordenProvision;
	}

	public void setOrdenProvision(OrdenModel ordenProvision) {
		this.ordenProvision = ordenProvision;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

    
}
