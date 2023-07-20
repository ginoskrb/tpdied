package gui.sucursal;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.grafo.MapaSucursales;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;

import sql.controllers.MapaController;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;

public class AgregarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args,PanelSucursal panel, MapaController mapa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarSucursal frame = new AgregarSucursal(panel, mapa);
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
	public AgregarSucursal(PanelSucursal panel, MapaController mapa) {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		campoNombre.setBounds(70, 63, 171, 20);
		contentPane.add(campoNombre);
		//--------------------------------------------//
		//array con las opciones de horas (00 a 23)
        String[] horas = new String[24];
        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d", i);
        }

        //array con las opciones de minutos (00 a 59)
        String[] minutos = new String[60];
        for (int i = 0; i < 60; i++) {
            minutos[i] = String.format("%02d", i);
        }
		
		//--------------------------------------------//
		JComboBox<String> comboHoras = new JComboBox<>(horas);
		comboHoras.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboHoras.setBounds(70, 138, 42, 22);
		contentPane.add(comboHoras);
		
		JComboBox<String> comboMinutos = new JComboBox<>(minutos);
		comboMinutos.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboMinutos.setBounds(122, 138, 47, 22);
		contentPane.add(comboMinutos);
		
		final String[] horaApertura = { "00:00" };
		
		comboHoras.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		comboMinutos.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		
		JComboBox<String> comboHoras2 = new JComboBox<>(horas);
		comboHoras2.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboHoras2.setBounds(70, 225, 42, 22);
		contentPane.add(comboHoras2);
		
		JComboBox<String> comboMinutos2 = new JComboBox<>(minutos);
		comboMinutos2.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboMinutos2.setBounds(122, 225, 47, 22);
		contentPane.add(comboMinutos2);
		
		final String[] horaCierre = { "00:00" };
		comboHoras2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));
		comboMinutos2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));
		
		JLabel nombreSucursal = new JLabel("Nombre");
		nombreSucursal.setForeground(new Color(255, 255, 255));
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 15));
		nombreSucursal.setBounds(70, 38, 58, 14);
		contentPane.add(nombreSucursal);
		
		JLabel horarioApertura = new JLabel("Horario apertura");
		horarioApertura.setForeground(new Color(255, 255, 255));
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 15));
		horarioApertura.setBounds(70, 113, 141, 14);
		contentPane.add(horarioApertura);
		
		JLabel horarioCierre = new JLabel("Horario cierre");
		horarioCierre.setForeground(new Color(255, 255, 255));
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 15));
		horarioCierre.setBounds(70, 190, 114, 14);
		contentPane.add(horarioCierre);
		
		JLabel estado = new JLabel("Estado");
		estado.setForeground(new Color(255, 255, 255));
		estado.setFont(new Font("Dialog", Font.BOLD, 15));
		estado.setBounds(70, 270, 90, 14);
		contentPane.add(estado);
		
		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setFont(new Font("Dialog", Font.PLAIN, 15));
		estadoTipo.setBounds(70, 295, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setBackground(new Color(255, 255, 255));
		botonAgregar.setForeground(new Color(0, 64, 128));
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBounds(112, 357, 99, 35);
		botonAgregar.addActionListener(e->{
					SucursalController sucursal = new SucursalController();
					sucursal.createSucursal(campoNombre.getText(), horaApertura[0], horaCierre[0],estadoTipo.getSelectedItem()=="Operativo");
					panel.getTablaSucursales().setModel(new SucursalController().generadorDeTabla());
					mapa.crearVertices();
					dispose();
		});
		contentPane.add(botonAgregar);
		
	}
	
	private void actualizarHora(String[] hora, JComboBox<String> comboHoras, JComboBox<String> comboMinutos) {
	    String horaSeleccionada = comboHoras.getSelectedItem().toString();
	    String minutosSeleccionados = comboMinutos.getSelectedItem().toString();
	    hora[0] = String.format("%02d:%02d", Integer.parseInt(horaSeleccionada), Integer.parseInt(minutosSeleccionados));
	}


	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}

	

	
}
