package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class panelCamino extends JPanel {
	private JTextField Buscador;
	/**
	 * Create the panel.
	 */
	public panelCamino() {
		setBounds(309,98,955,583);
		setLayout(null);
		
		
		//---------------------------------------------------------------//
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(new Color(195, 207, 217));
		botonAgregar.setBounds(167, 454, 143, 39);
		botonAgregar.setFocusPainted(false);
		botonAgregar.setBorder(new EmptyBorder(0,0,0,0));
		botonAgregar.addActionListener(e->{
			agregarCamino.main(null);
		});
		add(botonAgregar);
		//---------------------------------------------------------------//
		
		
		//---------------------------------------------------------------//
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEliminar.setBackground(new Color(195, 207, 217));
		botonEliminar.setBounds(357, 454, 143, 39);
		botonEliminar.setFocusPainted(false);
		botonEliminar.setBorder(new EmptyBorder(0,0,0,0));
		add(botonEliminar);
		//---------------------------------------------------------------//
		
		
		//---------------------------------------------------------------//
		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(new Color(195, 207, 217));
		botonEditar.setBounds(632, 454, 143, 39);
		botonEditar.setFocusPainted(false);
		botonEditar.setBorder(new EmptyBorder(0,0,0,0));
		add(botonEditar);
		//---------------------------------------------------------------//
		
		
		//---------------------------------------------------------------//
		Buscador = new JTextField();
		Buscador.setBounds(151, 76, 582, 27);
		add(Buscador);
		Buscador.setColumns(10);
		//---------------------------------------------------------------//
		
		
		//---------------------------------------------------------------//
		JButton botonBuscar = new JButton("");
		botonBuscar.setIcon(new ImageIcon("C:\\Users\\Franco\\Downloads\\buscar (1).png"));
		botonBuscar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		botonBuscar.setBackground(new Color(240, 240, 240));
		botonBuscar.setBounds(750, 69, 37, 34);
		botonBuscar.setFocusPainted(false);
		botonBuscar.setBorder(new EmptyBorder(0,0,0,0));
		add(botonBuscar);
		//---------------------------------------------------------------//
		
		
		//---------------------------------------------------------------//
		JScrollPane tablaDeDatos = new JScrollPane();
		tablaDeDatos.setBounds(151, 124, 636, 299);
		tablaDeDatos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(tablaDeDatos);
		
		JLabel lblNewLabel = new JLabel("Buscar camino (Nombre camino)");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 52, 257, 14);
		add(lblNewLabel);
		//---------------------------------------------------------------//
		
	}
}
