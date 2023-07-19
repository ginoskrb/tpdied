package gui.stock;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.controllers.ProductoController;
import sql.controllers.StockController;
import sql.models.StockModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarStock extends JFrame {

	private JPanel contentPane;
	private JTextField textStock;
	private StockController st = new StockController();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, StockSucursal menuPrincipal, int idStock, int idSucursal) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarStock frame = new EditarStock(menuPrincipal,idStock, idSucursal);
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
	public EditarStock(StockSucursal menuPrincipal, int idStock, int idSucursal) {
		StockModel stockActual = st.obtenerStockPorId(idStock);
		setTitle("Editar stock de producto: "+stockActual.getProducto().getNombre());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(607, 98);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblProducto = new JLabel("PRODUCTO");
		lblProducto.setForeground(Color.WHITE);
		lblProducto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProducto.setBounds(38, 24, 102, 14);
		contentPane.add(lblProducto);

		JLabel lblproducto = new JLabel(stockActual.getProducto().getNombre());
		lblproducto.setForeground(Color.WHITE);
		lblproducto.setFont(new Font("Dialog", Font.BOLD, 15));
		lblproducto.setBounds(147, 24, 102, 14);
		contentPane.add(lblproducto);

		JLabel lblNewLabel = new JLabel("STOCK");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(270, 24, 60, 14);
		contentPane.add(lblNewLabel);

		textStock = new JTextField();
		textStock.setFont(new Font("Dialog", Font.PLAIN, 15));
		textStock.setColumns(10);
		textStock.setBounds(342, 21, 86, 20);
		contentPane.add(textStock);

		JButton botonEditar = new JButton("Editar");
		botonEditar.setForeground(new Color(0, 64, 128));
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(Color.WHITE);
		botonEditar.setBounds(458, 14, 99, 35);
		contentPane.add(botonEditar);
		
		botonEditar.addActionListener(e -> {
			st.updateStock(idStock, Integer.parseInt(textStock.getText()));
			menuPrincipal.getTablaStock().setModel(st.generadorDeTabla(idSucursal));
			dispose();
		});
		
	}

}
