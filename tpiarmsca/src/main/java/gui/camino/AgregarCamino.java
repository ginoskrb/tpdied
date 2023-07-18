package gui.camino;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sql.controllers.CaminoController;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;

import javax.swing.JComboBox;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class AgregarCamino extends JFrame {

	private JPanel contentPane;
	private SucursalController suc = new SucursalController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCamino frame = new AgregarCamino();
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
	public AgregarCamino() {
		setTitle("Agregar Camino");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Lista de todas las sucursales
		List<SucursalModel> sucursales = suc.obtenerTodasLasSucursales();

		JComboBox<SucursalModel> sucOrigen = new JComboBox<>();
		sucOrigen.setBounds(100, 68, 135, 22);
		contentPane.add(sucOrigen);

		JComboBox<SucursalModel> sucDestino = new JComboBox<>();
		sucDestino.setBounds(100, 128, 135, 22);
		contentPane.add(sucDestino);

		for (SucursalModel sucursal : sucursales) {
			sucOrigen.addItem(sucursal);
			sucDestino.addItem(sucursal);
		}

		// array para las horas
		String[] horas = new String[73];
		for (int i = 0; i < 73; i++) {
			horas[i] = String.format("%02d", i);
		}

		JComboBox<String> tHoras = new JComboBox<String>(horas);
		tHoras.setBounds(100, 186, 56, 22);
		contentPane.add(tHoras);

		// array para los minutos
		String[] minutos = new String[60];
		for (int i = 0; i < 60; i++) {
			minutos[i] = String.format("%02d", i);
		}

		JComboBox<String> tMinutos = new JComboBox<String>(minutos);
		tMinutos.setBounds(166, 185, 56, 22);
		contentPane.add(tMinutos);

		final String[] tiempo = { "00:00" };

		tHoras.addActionListener(e -> actualizarHora(tiempo, tHoras, tMinutos));
		tMinutos.addActionListener(e -> actualizarHora(tiempo, tHoras, tMinutos));

		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(100, 315, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);

		JSlider capMaxima = new JSlider(0, 5000, 100);
		capMaxima.setBounds(100, 243, 95, 26);
		contentPane.add(capMaxima);

		JLabel lblSucursalOrigen = new JLabel("Sucursal origen");
		lblSucursalOrigen.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSucursalOrigen.setBounds(100, 43, 149, 14);
		contentPane.add(lblSucursalOrigen);

		JLabel lblSucursalDestino = new JLabel("Sucursal destino");
		lblSucursalDestino.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSucursalDestino.setBounds(100, 101, 114, 14);
		contentPane.add(lblSucursalDestino);

		JLabel lblTiempo = new JLabel("Tiempo transito");
		lblTiempo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTiempo.setBounds(100, 161, 114, 14);
		contentPane.add(lblTiempo);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEstado.setBounds(100, 290, 46, 14);
		contentPane.add(lblEstado);

		JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
		lblCapacidadMaxima.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCapacidadMaxima.setBounds(100, 218, 135, 14);
		contentPane.add(lblCapacidadMaxima);

		JLabel lblKg = new JLabel(capMaxima.getValue() + " kg");
		lblKg.setBounds(203, 247, 46, 22);
		contentPane.add(lblKg);

		capMaxima.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblKg.setText(capMaxima.getValue() + " kg");
			}
		});
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(123, 390, 99, 35);
		botonAgregar.addActionListener(e -> {
			CaminoController cam = new CaminoController();
			SucursalModel sucursalOrigen = (SucursalModel) sucOrigen.getSelectedItem();
			SucursalModel sucursalDestino = (SucursalModel) sucDestino.getSelectedItem();

			if (sucursalOrigen.getId() == sucursalDestino.getId()) {
				JOptionPane.showMessageDialog(null, "No se puede crear un camino desde una sucursal a si misma",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				cam.createCamino(sucursalOrigen, sucursalDestino, tiempo[0], capMaxima.getValue(),
						estadoTipo.getSelectedItem() == "Operativo");
				dispose();
			}

		});
		contentPane.add(botonAgregar);

	}

	private void actualizarHora(String[] hora, JComboBox<String> comboHoras, JComboBox<String> comboMinutos) {
		String horaSeleccionada = comboHoras.getSelectedItem().toString();
		String minutosSeleccionados = comboMinutos.getSelectedItem().toString();
		hora[0] = String.format("%02d:%02d", Integer.parseInt(horaSeleccionada),
				Integer.parseInt(minutosSeleccionados));
	}
}
