package gui.orden;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

public class PanelTablaDetalleOrden extends JPanel {

	/**
	 * Create the panel.
	 */

	public PanelTablaDetalleOrden() {
		setBounds(480, 22, 452, 538);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		
		JButton botonAsignarRuta = new JButton("Asignar ruta");
		botonAsignarRuta.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAsignarRuta.setForeground(new Color(255, 255, 255));
		botonAsignarRuta.setBackground(new Color(0, 64, 128));
		botonAsignarRuta.setBounds(140, 441, 167, 38);
		botonAsignarRuta.addActionListener(e->{
		});
		add(botonAsignarRuta);
		
		
	}
}
