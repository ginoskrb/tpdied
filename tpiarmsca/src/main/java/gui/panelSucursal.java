package gui;

import sql.controllers.SucursalController;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class panelSucursal extends JPanel {
	private JTextField Buscador;
	private JTable tablaSucursales = new JTable();
	private agregarSucursal ventanaAgregarSucursal = new agregarSucursal(this);
	private DefaultTableModel tablaBaseDeDatos= new SucursalController().generadorDeTabla();
	/**
	 * Create the panel.
	 */
	public panelSucursal() {
		setBounds(309,98,955,583);
		setLayout(null);
		
		
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
		
		JLabel lblNewLabel = new JLabel("Buscar sucursal (Nombre sucursal)");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 52, 257, 14);
		add(lblNewLabel);
		
		
		//---------------------------------------------------------------//
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 125, 624, 299);
		add(scrollPane);
		tablaSucursales.setModel(tablaBaseDeDatos);
		tablaSucursales.setModel(new SucursalController().generadorDeTabla());
		tablaSucursales.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaSucursales.getColumnModel().getColumn(3).setPreferredWidth(99);
		scrollPane.setViewportView(tablaSucursales);
		
		//---------------------------------------------------------------//
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(new Color(195, 207, 217));
		botonAgregar.setBounds(167, 454, 143, 39);
		botonAgregar.setFocusPainted(false);
		botonAgregar.setBorder(new EmptyBorder(0,0,0,0));
		botonAgregar.addActionListener(e->{
			ventanaAgregarSucursal.main(null,this);
		});
		add(botonAgregar);
		//---------------------------------------------------------------//
	
		
	
		
		//---------------------------------------------------------------//
		
		
	}
	public JTable getTablaSucursales() {
		return tablaSucursales;
	}
	public void setTablaSucursales(JTable tablaSucursales) {
		this.tablaSucursales = tablaSucursales;
	}
	public DefaultTableModel getTablaBaseDeDatos() {
		return tablaBaseDeDatos;
	}
	public void setTablaBaseDeDatos(DefaultTableModel tablaBaseDeDatos) {
		this.tablaBaseDeDatos = tablaBaseDeDatos;
	}
	
	
}
