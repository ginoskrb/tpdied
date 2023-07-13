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
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class agregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField campoID;
	private JTextField campoNombre;
	private JTextField campoPrecioUnitario;
	private JTextField campoPrecioKilogramos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarProducto frame = new agregarProducto();
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
	public agregarProducto() {
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
		
		campoID = new JTextField();
		campoID.setBounds(80, 64, 171, 20);
		contentPane.add(campoID);
		campoID.setColumns(10);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 120, 171, 20);
		contentPane.add(campoNombre);
		
		campoPrecioUnitario = new JTextField();
		campoPrecioUnitario.setColumns(10);
		campoPrecioUnitario.setBounds(80, 287, 171, 20);
		contentPane.add(campoPrecioUnitario);
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Dialog", Font.BOLD, 13));
		ID.setBounds(70, 39, 46, 14);
		contentPane.add(ID);
		
		JLabel nombreSucursal = new JLabel("Nombre");
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreSucursal.setBounds(70, 95, 149, 14);
		contentPane.add(nombreSucursal);
		
		JLabel horarioApertura = new JLabel("Descripcion");
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioApertura.setBounds(70, 151, 114, 14);
		contentPane.add(horarioApertura);
		
		JLabel horarioCierre = new JLabel("Precio unitario");
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioCierre.setBounds(70, 262, 114, 14);
		contentPane.add(horarioCierre);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(120, 401, 99, 35);
		botonAgregar.addActionListener(e->{
			dispose();
		});
		contentPane.add(botonAgregar);
		
		campoPrecioKilogramos = new JTextField();
		campoPrecioKilogramos.setColumns(10);
		campoPrecioKilogramos.setBounds(80, 343, 171, 20);
		contentPane.add(campoPrecioKilogramos);
		
		JLabel lblCapacidadMaxima = new JLabel("Precio kilogramos");
		lblCapacidadMaxima.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCapacidadMaxima.setBounds(70, 318, 135, 14);
		contentPane.add(lblCapacidadMaxima);
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setBounds(80, 176, 171, 75);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setWrapStyleWord(true);
		contentPane.add(campoDescripcion);
	}
}
