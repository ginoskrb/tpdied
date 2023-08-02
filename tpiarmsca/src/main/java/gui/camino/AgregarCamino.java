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
import sql.controllers.MapaController;
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
	public static void main(String[] args, PanelCamino panel,MapaController mapa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCamino frame = new AgregarCamino(panel,mapa);
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
	public AgregarCamino(PanelCamino panel,MapaController mapa) {
		setTitle("Agregar Camino");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
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
			if(!sucursal.getNombre().equals("PUERTO")) {
				sucDestino.addItem(sucursal);
			}
			if(!sucursal.getNombre().equals("CENTRAL")) {
				sucOrigen.addItem(sucursal);
			}
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
		lblSucursalOrigen.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSucursalOrigen.setBounds(100, 35, 149, 22);
		lblSucursalOrigen.setForeground(new Color(255, 255, 255));
		contentPane.add(lblSucursalOrigen);

		JLabel lblSucursalDestino = new JLabel("Sucursal destino");
		lblSucursalDestino.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSucursalDestino.setBounds(100, 101, 135, 16);
		lblSucursalDestino.setForeground(new Color(255, 255, 255));
		contentPane.add(lblSucursalDestino);

		JLabel lblTiempo = new JLabel("Tiempo transito");
		lblTiempo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTiempo.setBounds(100, 153, 114, 22);
		lblTiempo.setForeground(new Color(255, 255, 255));
		contentPane.add(lblTiempo);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEstado.setBounds(100, 280, 95, 24);
		lblEstado.setForeground(new Color(255, 255, 255));
		contentPane.add(lblEstado);

		JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
		lblCapacidadMaxima.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCapacidadMaxima.setBounds(100, 210, 135, 22);
		lblCapacidadMaxima.setForeground(new Color(255, 255, 255));
		contentPane.add(lblCapacidadMaxima);

		JLabel lblKg = new JLabel(capMaxima.getValue() + " kg");
		lblKg.setFont(new Font("Dialog", Font.BOLD, 15));
		lblKg.setBounds(203, 247, 72, 22);
		lblKg.setForeground(new Color(255, 255, 255));
		contentPane.add(lblKg);

		capMaxima.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblKg.setText(capMaxima.getValue() + " kg");
			}
		});
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setForeground(new Color(0, 64, 128));
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
				panel.getTablaCaminos().setModel(new CaminoController().generadorDeTabla());
				mapa.crearAristas();
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
