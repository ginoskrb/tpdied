package gui.stock;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.controllers.ProductoController;
import sql.controllers.StockController;
import sql.controllers.SucursalController;
import sql.models.ProductoModel;
import sql.models.SucursalModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class AgregarStock extends JFrame {

	private JPanel contentPane;
	private JTextField campoCantidadStock;
	private ProductoController prod = new ProductoController();
	private StockController st = new StockController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, StockSucursal menuPrincipal, int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarStock frame = new AgregarStock(menuPrincipal, id);
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
	public AgregarStock(StockSucursal menuPrincipal, int id) {
		setTitle("Agregar stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(590, 103);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		campoCantidadStock = new JTextField();
		campoCantidadStock.setFont(new Font("Dialog", Font.PLAIN, 15));
		campoCantidadStock.setBounds(341, 20, 86, 20);
		contentPane.add(campoCantidadStock);
		campoCantidadStock.setColumns(10);

		JLabel lblNewLabel = new JLabel("STOCK");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(271, 23, 60, 14);
		contentPane.add(lblNewLabel);

		JLabel lblProducto = new JLabel("PRODUCTO");
		lblProducto.setForeground(Color.WHITE);
		lblProducto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProducto.setBounds(30, 23, 102, 14);
		contentPane.add(lblProducto);

		List<ProductoModel> productos = prod.obtenerTodosLosProductos();

		JComboBox<ProductoModel> comboBoxProductos = new JComboBox();
		comboBoxProductos.setBounds(128, 20, 116, 19);
		contentPane.add(comboBoxProductos);

		for (ProductoModel p : productos) {
			comboBoxProductos.addItem(p);
		}

		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setForeground(new Color(0, 64, 128));
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(Color.WHITE);
		botonAgregar.setBounds(453, 13, 99, 35);
		botonAgregar.addActionListener(e -> {
			st.createStock((ProductoModel) comboBoxProductos.getSelectedItem(),
					new SucursalController().obtenerSucursalPorId(id), Integer.parseInt(campoCantidadStock.getText()));
			menuPrincipal.getTablaStock().setModel(new StockController().generadorDeTabla(id));
			dispose();
		});
		contentPane.add(botonAgregar);
	}
}
