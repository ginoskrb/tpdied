package gui.producto;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import sql.controllers.ProductoController;
import sql.controllers.SucursalController;
import sql.models.ProductoModel;

public class EditarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private JTextField campoPrecioUnitario;
	private JTextField campoPesoKilogramos;
	private ProductoController prodEditar = new ProductoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, PanelProducto panel, int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProducto frame = new EditarProducto(panel, id);
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
	public EditarProducto(PanelProducto panel, int id) {
		setTitle("EDITAR PRODUCTO #"+id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(80, 79, 171, 20);
		campoNombre.setText(prodEditar.getAtributoProducto(id, "nombre"));
		contentPane.add(campoNombre);
		
		campoPrecioUnitario = new JTextField();
		campoPrecioUnitario.setColumns(10);
		campoPrecioUnitario.setBounds(80, 256, 171, 20);
		campoPrecioUnitario.setText(prodEditar.getAtributoProducto(id, "precio_unitario"));
		contentPane.add(campoPrecioUnitario);
		
		campoPesoKilogramos = new JTextField();
		campoPesoKilogramos.setColumns(10);
		campoPesoKilogramos.setBounds(80, 312, 171, 20);
		campoPesoKilogramos.setText(prodEditar.getAtributoProducto(id, "peso_kg"));
		contentPane.add(campoPesoKilogramos);
		
		JLabel pesoKilogramos = new JLabel("Peso en kilogramos");
		pesoKilogramos.setFont(new Font("Dialog", Font.BOLD, 13));
		pesoKilogramos.setBounds(70, 287, 135, 14);
		contentPane.add(pesoKilogramos);
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setBounds(80, 135, 171, 75);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setWrapStyleWord(true);
		campoDescripcion.setText(prodEditar.getAtributoProducto(id, "descripcion"));
		contentPane.add(campoDescripcion);
		
		JLabel nombreProducto = new JLabel("Nombre");
		nombreProducto.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreProducto.setBounds(70, 54, 149, 14);
		contentPane.add(nombreProducto);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setFont(new Font("Dialog", Font.BOLD, 13));
		descripcion.setBounds(70, 110, 114, 14);
		contentPane.add(descripcion);
		
		JLabel precioUnitario = new JLabel("Precio unitario");
		precioUnitario.setFont(new Font("Dialog", Font.BOLD, 13));
		precioUnitario.setBounds(70, 231, 114, 14);
		contentPane.add(precioUnitario);
		
		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonEditar.setBounds(120, 378, 99, 35);
		botonEditar.addActionListener(e->{
			prodEditar.updateProducto(id, new Object[] { campoNombre.getText(), campoDescripcion.getText(),Float.parseFloat(campoPrecioUnitario.getText()),Float.parseFloat(campoPesoKilogramos.getText())});
			panel.getTablaProductos().setModel(new ProductoController().generadorDeTabla());
			dispose();
		});
		contentPane.add(botonEditar);


	}

}
