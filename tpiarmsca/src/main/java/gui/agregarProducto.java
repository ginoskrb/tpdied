package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sql.controllers.ProductoController;
import sql.controllers.SucursalController;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class agregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private JTextField campoPrecioUnitario;
	private JTextField campoPesoKilogramos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, panelProducto panel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarProducto frame = new agregarProducto(panel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public agregarProducto(panelProducto panel) {
		setTitle("Registro");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350,500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 79, 171, 20);
		contentPane.add(campoNombre);
		
		campoPrecioUnitario = new JTextField();
		campoPrecioUnitario.setColumns(10);
		campoPrecioUnitario.setBounds(80, 256, 171, 20);
		contentPane.add(campoPrecioUnitario);
		
		campoPesoKilogramos = new JTextField();
		campoPesoKilogramos.setColumns(10);
		campoPesoKilogramos.setBounds(80, 312, 171, 20);
		contentPane.add(campoPesoKilogramos);
		
		JLabel pesoKilogramos = new JLabel("Peso en kilogramos");
		pesoKilogramos.setFont(new Font("Dialog", Font.BOLD, 13));
		pesoKilogramos.setBounds(70, 287, 135, 14);
		contentPane.add(pesoKilogramos);
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setBounds(80, 135, 171, 75);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setWrapStyleWord(true);
		contentPane.add(campoDescripcion);
		
		JLabel nombreProducto = new JLabel("Nombre");
		nombreProducto.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreProducto.setBounds(70, 54, 149, 14);
		contentPane.add(nombreProducto);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setFont(new Font("Dialog", Font.BOLD, 13));
		descripcion.setBounds(70, 110, 114, 14);
		contentPane.add(descripcion);
		
		JLabel precioUnitario = new JLabel("Precio unitario");
		precioUnitario.setFont(new Font("Dialog", Font.BOLD, 13));
		precioUnitario.setBounds(70, 231, 114, 14);
		contentPane.add(precioUnitario);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(120, 378, 99, 35);
		botonAgregar.addActionListener(e->{
			ProductoController producto = new ProductoController();
			producto.createProducto(campoNombre.getText(),campoDescripcion.getText(),Float.parseFloat(campoPrecioUnitario.getText()),Float.parseFloat(campoPesoKilogramos.getText()));
			panel.getTablaProductos().setModel(new ProductoController().generadorDeTabla());
			dispose();
		});
		contentPane.add(botonAgregar);
		
	}
}
