package gui.orden;

import sql.models.ProductoModel;

public class ProductoCantidad {
	private ProductoModel producto;
	private int cantidad;
	
	public ProductoCantidad(ProductoModel producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public ProductoModel getProducto() {
		return producto;
	}
	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
