package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;

public class editarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField campoID;
	private JTextField campoNombre;
	private JTextField campoHorarioApertura;
	private JTextField campoHorarioCierre;
	private SucursalController sucEditar = new SucursalController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,panelSucursal panel,int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editarSucursal frame = new editarSucursal(panel,id);
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
	public editarSucursal(panelSucursal panel,int id) {
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
		campoID.setText("id");
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 138, 171, 20);
		contentPane.add(campoNombre);
		campoNombre.setText(sucEditar.getAtributoSucursal(id, "nombre"));
		
		campoHorarioApertura = new JTextField();
		campoHorarioApertura.setColumns(10);
		campoHorarioApertura.setBounds(80, 208, 171, 20);
		contentPane.add(campoHorarioApertura);
		campoHorarioApertura.setText(sucEditar.getAtributoSucursal(id, "hapertura"));
		
		campoHorarioCierre = new JTextField();
		campoHorarioCierre.setColumns(10);
		campoHorarioCierre.setBounds(80, 277, 171, 20);
		contentPane.add(campoHorarioCierre);
		campoHorarioCierre.setText(sucEditar.getAtributoSucursal(id, "hcierre"));
		
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
		
		JLabel estado = new JLabel("Estado");
		estado.setFont(new Font("Dialog", Font.BOLD, 13));
		estado.setBounds(70, 332, 46, 14);
		contentPane.add(estado);
		
		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(137, 329, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);
		
		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonEditar.setBounds(120, 389, 99, 35);
		botonEditar.addActionListener(e->{
			panel.getTablaSucursales().setModel(new SucursalController().generadorDeTabla());
			dispose();
		});
		contentPane.add(botonEditar);
	}

	public JTextField getCampoID() {
		return campoID;
	}

	public void setCampoID(JTextField campoID) {
		this.campoID = campoID;
	}

	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}

	public JTextField getCampoHorarioApertura() {
		return campoHorarioApertura;
	}

	public void setCampoHorarioApertura(JTextField campoHorarioApertura) {
		this.campoHorarioApertura = campoHorarioApertura;
	}

	public JTextField getCampoHorarioCierre() {
		return campoHorarioCierre;
	}

	public void setCampoHorarioCierre(JTextField campoHorarioCierre) {
		this.campoHorarioCierre = campoHorarioCierre;
	}
	
}