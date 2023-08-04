package gui.orden;


import java.awt.*;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import gui.grafo.MapaSucursales;
import gui.grafo.Mapa;

@SuppressWarnings("serial")
public class PanelOrdenProvision extends JPanel {
	
	/**
	 * Create the panel.
	 */
	private PanelTablaOrden pto;
	public PanelOrdenProvision(MapaSucursales mapa, DefaultTableModel modeloTabla) {
		setBounds(309, 98, 955, 583);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		add(new Mapa(mapa));
		pto = new PanelTablaOrden(mapa,modeloTabla);
		add(pto);
	}
	public PanelTablaOrden getPto() {
		return pto;
	}
	
	
}
