package gui.orden;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sql.controllers.OrdenController;
import sql.controllers.StockController;
import sql.models.DetalleOrdenModel;

public class OrdenYSucursalStock {
	
	private Map<Integer,Integer> orden;
	private HashMap<Integer,HashMap<Integer,Integer>> sucursales;
	private OrdenController ordController = new OrdenController();
	private List<DetalleOrdenModel> ordModel;
	private StockController stController = new StockController();
	
	public OrdenYSucursalStock(int idOrden) {
		ordModel = ordController.obtenerProductosSolicitados(idOrden);
		orden = new HashMap<>();
		sucursales = new HashMap<>();
	}
	
	public void listaProductosOrden(){
		for(DetalleOrdenModel i: ordModel){
			orden.put(i.getProducto().getId(), i.getCantidad());
		}
	}
	
	public void listaSucursalesStock() {
		sucursales = stController.obtenerSucursalesYListaStock();	
	}
	public void listaProductosSucursal(){
		
	}

	public Map<Integer, Integer> getOrden() {
		return orden;
	}

	public void setOrden(Map<Integer, Integer> orden) {
		this.orden = orden;
	}

	public HashMap<Integer, HashMap<Integer, Integer>> getSucursales() {
		return sucursales;
	}

	
	
}
	
	 
	
