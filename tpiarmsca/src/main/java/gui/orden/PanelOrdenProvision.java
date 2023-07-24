package gui.orden;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import gui.grafo.MapaSucursales;
import gui.grafo.Mapa;

public class PanelOrdenProvision extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public PanelOrdenProvision(MapaSucursales mapa) {
		setBounds(309, 98, 955, 583);
		setBackground(new Color(255, 255, 230));
		setLayout(null);
		add(new Mapa(mapa));
		add(new PanelTablaOrden(mapa));
	}
	
	
}
