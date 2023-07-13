package gui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class agregarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField campoID;
	private JTextField campoSucursalOrigen;
	private JTextField campoSucursalDestino;
	private JTextField campoTiempoTransito;
	private JTextField campoCapacidadMaxima;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarCamino frame = new agregarCamino();
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
	public agregarCamino() {
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
		
		campoSucursalOrigen = new JTextField();
		campoSucursalOrigen.setColumns(10);
		campoSucursalOrigen.setBounds(80, 120, 171, 20);
		contentPane.add(campoSucursalOrigen);
		
		campoSucursalDestino = new JTextField();
		campoSucursalDestino.setColumns(10);
		campoSucursalDestino.setBounds(80, 176, 171, 20);
		contentPane.add(campoSucursalDestino);
		
		campoTiempoTransito = new JTextField();
		campoTiempoTransito.setColumns(10);
		campoTiempoTransito.setBounds(80, 232, 171, 20);
		contentPane.add(campoTiempoTransito);
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Dialog", Font.BOLD, 13));
		ID.setBounds(70, 39, 46, 14);
		contentPane.add(ID);
		
		JLabel nombreSucursal = new JLabel("Sucursal origen");
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreSucursal.setBounds(70, 95, 149, 14);
		contentPane.add(nombreSucursal);
		
		JLabel horarioApertura = new JLabel("Sucursal destino");
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioApertura.setBounds(70, 151, 114, 14);
		contentPane.add(horarioApertura);
		
		JLabel horarioCierre = new JLabel("Tiempo transito");
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioCierre.setBounds(70, 207, 114, 14);
		contentPane.add(horarioCierre);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(80, 336, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(120, 401, 99, 35);
		botonAgregar.addActionListener(e->{
			dispose();
		});
		contentPane.add(botonAgregar);
		
		campoCapacidadMaxima = new JTextField();
		campoCapacidadMaxima.setColumns(10);
		campoCapacidadMaxima.setBounds(80, 288, 171, 20);
		contentPane.add(campoCapacidadMaxima);
		
		JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
		lblCapacidadMaxima.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCapacidadMaxima.setBounds(70, 263, 135, 14);
		contentPane.add(lblCapacidadMaxima);
		
		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(136, 333, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);
		
	}
}
