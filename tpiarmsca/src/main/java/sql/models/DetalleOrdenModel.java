package sql.models; // DetallerOrdenModel

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sql.models.ProductoModel;
import sql.models.OrdenModel;

@Entity
@Table(name = "detalle_orden_provision")
public class DetalleOrdenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_orden_provision", referencedColumnName = "id")
    private OrdenModel ordenProvision;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private ProductoModel producto;

    @Column(name = "cantidad_requerida")
    private int cantidad;

    public DetalleOrdenModel() {
    	
    }
    
	public DetalleOrdenModel(OrdenModel ordenProvision, ProductoModel producto, int cantidad) {
		this.ordenProvision = ordenProvision;
		this.producto = producto;
		this.cantidad = cantidad;
	}

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

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProductoId(ProductoModel producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

    
}
