package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;

public class agregarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField campoID;
	private JTextField campoNombre;
	private JTextField campoHorarioApertura;
	private JTextField campoHorarioCierre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarSucursal frame = new agregarSucursal();
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
	public agregarSucursal() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350,500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoID = new JTextField();
		campoID.setBounds(80, 68, 171, 20);
		contentPane.add(campoID);
		campoID.setColumns(10);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 138, 171, 20);
		contentPane.add(campoNombre);
		
		campoHorarioApertura = new JTextField();
		campoHorarioApertura.setColumns(10);
		campoHorarioApertura.setBounds(80, 208, 171, 20);
		contentPane.add(campoHorarioApertura);
		
		campoHorarioCierre = new JTextField();
		campoHorarioCierre.setColumns(10);
		campoHorarioCierre.setBounds(80, 277, 171, 20);
		contentPane.add(campoHorarioCierre);
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Dialog", Font.BOLD, 13));
		ID.setBounds(70, 43, 46, 14);
		contentPane.add(ID);
		
		JLabel nombreSucursal = new JLabel("Nombre");
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreSucursal.setBounds(70, 113, 58, 14);
		contentPane.add(nombreSucursal);
		
		JLabel horarioApertura = new JLabel("Horario apertura");
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioApertura.setBounds(70, 183, 114, 14);
		contentPane.add(horarioApertura);
		
		JLabel horarioCierre = new JLabel("Horario cierre");
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioCierre.setBounds(70, 252, 90, 14);
		contentPane.add(horarioCierre);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(70, 332, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(137, 329, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(120, 389, 99, 35);
		botonAgregar.addActionListener(e->{
			dispose();
		});
		contentPane.add(botonAgregar);
	}
}
