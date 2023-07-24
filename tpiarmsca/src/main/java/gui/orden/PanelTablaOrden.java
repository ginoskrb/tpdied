package gui.orden;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import gui.grafo.MapaSucursales;
import sql.controllers.OrdenController;
import sql.controllers.ProductoController;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

public class PanelTablaOrden extends JPanel {

	private JTable tablaOrden = new JTable();
	/**
	 * Create the panel.
	 */

	public PanelTablaOrden(MapaSucursales mapa) {
		setBounds(480, 22, 452, 538);
		setBackground(new Color(255, 255, 128));
		
		
		
		JButton botonAsignarRuta = new JButton("Asignar ruta");
		botonAsignarRuta.setBounds(140, 441, 167, 38);
		botonAsignarRuta.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAsignarRuta.setForeground(new Color(255, 255, 255));
		botonAsignarRuta.setBackground(new Color(0, 64, 128));
		botonAsignarRuta.addActionListener(e->{
			new VentanaCaminosPosibles(mapa).main(null,mapa);
		});
		setLayout(null);
		add(botonAsignarRuta);
		
		tablaOrden.setModel(new OrdenController().generadorDeTabla());
		tablaOrden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrden.getTableHeader().setBackground(new Color(0,64,128));
		tablaOrden.getTableHeader().setForeground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 401, 376, -355);
		scrollPane.setViewportView(tablaOrden);
		setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.CENTER);
		
	}
}
