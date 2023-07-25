package gui.orden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import gui.grafo.MapaSucursales;
import gui.grafo.Mapa;

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
