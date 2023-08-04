package gui.orden;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.grafo.MapaSucursales;
import sql.controllers.MapaController;
import sql.controllers.OrdenController;
import sql.controllers.SucursalController;
import sql.models.OrdenModel;
import sql.models.SucursalModel;

import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class PanelTablaOrden extends JPanel {

	private JTable tablaOrden = new JTable();
	private OrdenYSucursalStock p;

	/**
	 * Create the panel.
	 */

	public PanelTablaOrden(MapaSucursales mapa, DefaultTableModel modeloTabla) {
		setBounds(480, 22, 452, 538);
		setBackground(new Color(255, 255, 255));

		tablaOrden.setModel(modeloTabla);
		tablaOrden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrden.getTableHeader().setBackground(new Color(0, 64, 128));
		tablaOrden.getTableHeader().setForeground(Color.white);

		JButton botonAsignarRuta = new JButton("Asignar ruta");
		botonAsignarRuta.setBounds(33, 489, 167, 38);
		botonAsignarRuta.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAsignarRuta.setForeground(new Color(255, 255, 255));
		botonAsignarRuta.setBackground(new Color(0, 64, 128));
		botonAsignarRuta.addActionListener(e -> {
			OrdenModel destino;
			int filaSeleccionada = tablaOrden.getSelectedRow();
			if (filaSeleccionada != -1) {
				Object idSeleccionado = tablaOrden.getValueAt(filaSeleccionada, 0);
				p = new OrdenYSucursalStock(Integer.parseInt(idSeleccionado.toString()));
				destino = new OrdenController().obtenerOrdenPorId(Integer.parseInt(idSeleccionado.toString()));
				p.listaProductosOrden();
				p.listaSucursalesStock();
				ArrayList<String> origenes = new ArrayList<>();
				HashMap<Integer, HashMap<Integer, Integer>> listaSucursalesValidas = p.getSucursales();
				ArrayList<Integer> keysDelete = new ArrayList<>();
				for (HashMap.Entry<Integer, Integer> x : p.getOrden().entrySet()) {
					for (HashMap.Entry<Integer, HashMap<Integer, Integer>> y : p.getSucursales().entrySet()) {
						HashMap<Integer, Integer> yInterno = y.getValue();
						if (yInterno.containsKey(x.getKey())) {
							if (x.getValue() > yInterno.get(x.getKey())) {
								keysDelete.add(y.getKey());
							}
						} else {
							keysDelete.add(y.getKey());
						}
					}
				}
				for (Integer z : keysDelete) {
					listaSucursalesValidas.remove(z);
				}

				for (HashMap.Entry<Integer, HashMap<Integer, Integer>> y : listaSucursalesValidas.entrySet()) {
					origenes.add(String.valueOf(y.getKey()));
				}
				
				ArrayList<SucursalModel> sc = (ArrayList<SucursalModel>) new SucursalController().obtenerTodasLasSucursales();
				for(SucursalModel suc: sc) {
					if(suc.getNombre().equals("PUERTO")) {
						origenes.add(String.valueOf(suc.getId()));
					}
				}
				Integer idOrden = Integer.parseInt(idSeleccionado.toString());
				new MapaController(mapa).crearAristas();
				
				new VentanaCaminosPosibles(mapa, String.valueOf(destino.getSucursalDestino().getId()), origenes,
						destino.getTiempoMaximo(), idOrden,tablaOrden).main(null, mapa,
								String.valueOf(destino.getSucursalDestino().getId()), origenes,
								destino.getTiempoMaximo(), idOrden,tablaOrden);
			}
		});
		setLayout(null);
		setLayout(null);
		add(botonAsignarRuta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 452, 478);
		scrollPane.setViewportView(tablaOrden);
		add(scrollPane);
		
		JButton botonOrdenes = new JButton("Ordenes en Proceso");
		botonOrdenes.setForeground(Color.WHITE);
		botonOrdenes.setFont(new Font("Dialog", Font.BOLD, 15));
		botonOrdenes.setBackground(new Color(0, 64, 128));
		botonOrdenes.setBounds(232, 489, 196, 38);
		botonOrdenes.addActionListener(e-> {
			OrdenesEnProceso.main(null);
		});
		add(botonOrdenes);

	}

	public JTable getTablaOrden() {
		return tablaOrden;
	}
}
