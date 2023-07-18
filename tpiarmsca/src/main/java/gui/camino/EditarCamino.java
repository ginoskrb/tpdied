package gui.camino;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.sucursal.EditarSucursal;
import gui.sucursal.PanelSucursal;
import sql.controllers.CaminoController;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;
@SuppressWarnings("serial")
public class EditarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private CaminoController camEditar = new CaminoController();
	private SucursalController suc = new SucursalController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, PanelCamino panel, int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCamino frame = new EditarCamino(panel, id);
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
	public EditarCamino(PanelCamino panel, int id) {
		setTitle("EDITAR CAMINO #" + id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// --------------------------------------------//
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

		establecerHora(camEditar.getAtributoCamino(id, "tiempoTransito"), tHoras, tMinutos, horas, minutos);

		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(100, 315, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);

		JSlider capMaxima = new JSlider(0, 5000, 100);
		capMaxima.setBounds(100, 243, 95, 26);
		contentPane.add(capMaxima);

		// --------------------------------------------//

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

		// --------------------------------------------//

		capMaxima.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblKg.setText(capMaxima.getValue() + " kg");
			}
		});

		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonEditar.setBounds(123, 390, 99, 35);
		botonEditar.addActionListener(e -> {
			SucursalModel sucursalOrigen = (SucursalModel) sucOrigen.getSelectedItem();
			SucursalModel sucursalDestino = (SucursalModel) sucDestino.getSelectedItem();
			camEditar.updateCamino(id, sucursalOrigen, sucursalDestino, tiempo[0],
				capMaxima.getValue(), estadoTipo.getSelectedItem() == "Operativo");
			panel.getTablaCaminos().setModel(new CaminoController().generadorDeTabla());
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
		hora[0] = String.format("%02d:%02d", Integer.parseInt(horaSeleccionada),
				Integer.parseInt(minutosSeleccionados));
	}

	private void establecerHora(String tiempoActual, JComboBox<String> comboHoras, JComboBox<String> comboMinutos,
			String[] horas, String[] minutos) {
		int horaSeleccionadaIndex = -1;
		int minutosSeleccionadosIndex = -1;

		for (int i = 0; i < horas.length; i++) {
			if (horas[i].equals(tiempoActual.substring(0, 2))) {
				horaSeleccionadaIndex = i;
				break;
			}
		}

		for (int i = 0; i < minutos.length; i++) {
			if (minutos[i].equals(tiempoActual.substring(3, 5))) {
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