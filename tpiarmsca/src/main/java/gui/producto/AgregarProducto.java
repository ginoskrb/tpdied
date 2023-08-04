package gui.producto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sql.controllers.ProductoController;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private JTextField campoPrecioUnitario;
	private JTextField campoPesoKilogramos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, PanelProducto panel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarProducto frame = new AgregarProducto(panel);
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
	public AgregarProducto(PanelProducto panel) {
		setTitle("Registro");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350,500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNombre = new JTextField();
		campoNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 79, 171, 20);
		contentPane.add(campoNombre);
		
		campoPrecioUnitario = new JTextField();
		campoPrecioUnitario.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoPrecioUnitario.setColumns(10);
		campoPrecioUnitario.setBounds(80, 256, 171, 20);
		contentPane.add(campoPrecioUnitario);
		
		campoPesoKilogramos = new JTextField();
		campoPesoKilogramos.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoPesoKilogramos.setColumns(10);
		campoPesoKilogramos.setBounds(80, 312, 171, 20);
		contentPane.add(campoPesoKilogramos);
		
		JLabel pesoKilogramos = new JLabel("Peso en kilogramos");
		pesoKilogramos.setForeground(new Color(255, 255, 255));
		pesoKilogramos.setFont(new Font("Dialog", Font.BOLD, 15));
		pesoKilogramos.setBounds(70, 287, 135, 14);
		contentPane.add(pesoKilogramos);
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoDescripcion.setBounds(80, 135, 171, 75);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setWrapStyleWord(true);
		contentPane.add(campoDescripcion);
		
		JLabel nombreProducto = new JLabel("Nombre");
		nombreProducto.setForeground(new Color(255, 255, 255));
		nombreProducto.setFont(new Font("Dialog", Font.BOLD, 15));
		nombreProducto.setBounds(70, 54, 149, 14);
		contentPane.add(nombreProducto);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setForeground(new Color(255, 255, 255));
		descripcion.setFont(new Font("Dialog", Font.BOLD, 15));
		descripcion.setBounds(70, 110, 114, 14);
		contentPane.add(descripcion);
		
		JLabel precioUnitario = new JLabel("Precio unitario");
		precioUnitario.setForeground(new Color(255, 255, 255));
		precioUnitario.setFont(new Font("Dialog", Font.BOLD, 15));
		precioUnitario.setBounds(70, 231, 114, 14);
		contentPane.add(precioUnitario);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setForeground(new Color(0, 64, 128));
		botonAgregar.setBackground(new Color(255, 255, 255));
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
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
