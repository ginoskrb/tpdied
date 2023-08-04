package gui.sucursal;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import sql.controllers.SucursalController;

@SuppressWarnings("serial")
public class EditarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private SucursalController sucEditar = new SucursalController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, PanelSucursal panel, int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarSucursal frame = new EditarSucursal(panel, id);
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
	public EditarSucursal(PanelSucursal panel, int id) {
		setTitle("EDITAR SUCURSAL #"+id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		campoNombre = new JTextField();
		campoNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoNombre.setColumns(10);
		campoNombre.setBounds(70, 67, 171, 20);
		contentPane.add(campoNombre);
		campoNombre.setText(sucEditar.getAtributoSucursal(id, "nombre"));

		// --------------------------------------------//
		// array con las opciones de horas (00 a 23)
		String[] horas = new String[24];
		for (int i = 0; i < 24; i++) {
			horas[i] = String.format("%02d", i);
		}

		// array con las opciones de minutos (00 a 59)
		String[] minutos = new String[60];
		for (int i = 0; i < 60; i++) {
			minutos[i] = String.format("%02d", i);
		}

		// --------------------------------------------//
		JComboBox<String> comboHoras = new JComboBox<>(horas);
		comboHoras.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboHoras.setBounds(70, 142, 42, 22);
		contentPane.add(comboHoras);

		JComboBox<String> comboMinutos = new JComboBox<>(minutos);
		comboMinutos.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboMinutos.setBounds(122, 142, 47, 22);
		contentPane.add(comboMinutos);

		final String[] horaApertura = { "00:00" };

		comboHoras.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		comboMinutos.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		
		establecerHora(sucEditar.getAtributoSucursal(id, "hapertura"),comboHoras,comboMinutos,horas,minutos);

		JComboBox<String> comboHoras2 = new JComboBox<>(horas);
		comboHoras2.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboHoras2.setBounds(70, 227, 42, 22);
		contentPane.add(comboHoras2);

		JComboBox<String> comboMinutos2 = new JComboBox<>(minutos);
		comboMinutos2.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboMinutos2.setBounds(122, 227, 47, 22);
		contentPane.add(comboMinutos2);

		final String[] horaCierre = { "00:00" };
		comboHoras2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));
		comboMinutos2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));

		establecerHora(sucEditar.getAtributoSucursal(id, "hcierre"),comboHoras2,comboMinutos2,horas,minutos);

		JLabel nombreSucursal = new JLabel("Nombre");
		nombreSucursal.setForeground(new Color(255, 255, 255));
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 15));
		nombreSucursal.setBounds(70, 42, 58, 14);
		contentPane.add(nombreSucursal);

		JLabel horarioApertura = new JLabel("Horario apertura");
		horarioApertura.setForeground(new Color(255, 255, 255));
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 15));
		horarioApertura.setBounds(70, 117, 142, 14);
		contentPane.add(horarioApertura);

		JLabel horarioCierre = new JLabel("Horario cierre");
		horarioCierre.setForeground(new Color(255, 255, 255));
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 15));
		horarioCierre.setBounds(70, 202, 129, 14);
		contentPane.add(horarioCierre);

		JLabel estado = new JLabel("Estado");
		estado.setForeground(new Color(255, 255, 255));
		estado.setFont(new Font("Dialog", Font.BOLD, 15));
		estado.setBounds(70, 278, 78, 14);
		contentPane.add(estado);

		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setFont(new Font("Dialog", Font.PLAIN, 15));
		estadoTipo.setBounds(70, 303, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);

		JButton botonEditar = new JButton("Editar");
		botonEditar.setBackground(new Color(255, 255, 255));
		botonEditar.setForeground(new Color(0, 64, 128));
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBounds(113, 366, 99, 35);
		botonEditar.addActionListener(e -> {
			sucEditar.updateSucursal(id, new String[] { campoNombre.getText(), horaApertura[0],horaCierre[0],String.valueOf(estadoTipo.getSelectedItem()=="Operativo")});
			panel.getTablaSucursales().setModel(new SucursalController().generadorDeTabla());
			dispose();
			
		});
		contentPane.add(botonEditar);
	}


	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}

	private void actualizarHora(String[] hora, JComboBox<String> comboHoras, JComboBox<String> comboMinutos) {
	    String horaSeleccionada = comboHoras.getSelectedItem().toString();
	    String minutosSeleccionados = comboMinutos.getSelectedItem().toString();
	    hora[0] = String.format("%02d:%02d", Integer.parseInt(horaSeleccionada), Integer.parseInt(minutosSeleccionados));
	}
	
	private void establecerHora(String horaDB, JComboBox<String> comboHoras, JComboBox<String> comboMinutos,String[] horas,String[] minutos) {
	    int horaSeleccionadaIndex = -1;
	    int minutosSeleccionadosIndex = -1;

	    for (int i = 0; i < horas.length; i++) {
	        if (horas[i].equals(horaDB.substring(0, 2))) {
	            horaSeleccionadaIndex = i;
	            break;
	        }
	    }

	    for (int i = 0; i < minutos.length; i++) {
	        if (minutos[i].equals(horaDB.substring(3, 5))) {
	            minutosSeleccionadosIndex = i;
	            break;
	        }
	    }

	    if (horaSeleccionadaIndex != -1 && minutosSeleccionadosIndex != -1) {
	        comboHoras.setSelectedIndex(horaSeleccionadaIndex);
	        comboMinutos.setSelectedIndex(minutosSeleccionadosIndex);
	    }
	}

}
