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
@Table(name = "stock_producto_sucursal")
public class StockModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id")
	private ProductoModel producto;
	
	@ManyToOne
	@JoinColumn(name = "id_sucursal", referencedColumnName = "id")
	private SucursalModel sucursal;
	
	@Column(name="cantidad_stock")
	private int stock;
	
	
	public StockModel() {
	}


	public StockModel(ProductoModel producto, SucursalModel sucursal, int stock) {
		this.producto = producto;
		this.sucursal = sucursal;
		this.stock = stock;
	}

// GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	
	public ProductoModel getProducto() {
		return producto;
	}


	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}


	public SucursalModel getSucursal() {
		return sucursal;
	}


	public void setSucursal(SucursalModel sucursal) {
		this.sucursal = sucursal;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
		
}
