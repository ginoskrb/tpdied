package gui.stock;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.sucursal.EditarSucursal;
import gui.sucursal.PanelSucursal;
import sql.controllers.StockController;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StockSucursal extends JFrame {

	private JPanel contentPane;
	private JTable tablaStock;
	private JTextField buscadorProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int suc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockSucursal frame = new StockSucursal(suc);
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
	public StockSucursal(int suc) {
		setTitle("Menu de stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(700,518);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 664, 362);
		contentPane.add(scrollPane);
		
		tablaStock = new JTable();
		tablaStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablaStock.setModel(new StockController().generadorDeTabla(suc));
		scrollPane.setViewportView(tablaStock);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setForeground(new Color(0, 64, 128));
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(Color.WHITE);
		botonAgregar.setBounds(111, 433, 99, 35);
		botonAgregar.addActionListener(e->{
			AgregarStock.main(null, this, suc);
		});
		contentPane.add(botonAgregar);
		
		JButton botonEditar = new JButton("Editar");
		botonEditar.setForeground(new Color(0, 64, 128));
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(Color.WHITE);
		botonEditar.setBounds(288, 433, 99, 35);
		contentPane.add(botonEditar);
		botonEditar.addActionListener(e->{
			int filaSeleccionada = tablaStock.getSelectedRow();
			if (filaSeleccionada != -1) {
				
                Object idSeleccionado = tablaStock.getValueAt(filaSeleccionada, 0);
                EditarStock.main(null,this,Integer.parseInt(idSeleccionado.toString()),suc);
                }
			
		});
		
		JButton botonEliminar = new JButton("Eliminar");
		
		botonEliminar.setForeground(new Color(0, 64, 128));
		botonEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEliminar.setBackground(Color.WHITE);
		botonEliminar.setBounds(471, 433, 99, 35);
		botonEliminar.addActionListener( e-> {
				int filaSeleccionada = tablaStock.getSelectedRow();
				if (filaSeleccionada != -1) {
					
                    Object idSeleccionado = tablaStock.getValueAt(filaSeleccionada, 0);
                    int op = JOptionPane.showOptionDialog(null,"Estas seguro que deseas eliminar este producto?","Avertencia",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] {"Si","No"},"Si");
                    switch(op) {
                    	case JOptionPane.YES_OPTION: 
                    		new StockController().deleteStock(Integer.parseInt(idSeleccionado.toString()));
                    		tablaStock.setModel(new StockController().generadorDeTabla(suc));
                    		break;
                    	case JOptionPane.NO_OPTION: 
                    		break;
                    }
                } 
            });
		contentPane.add(botonEliminar);
		
		JLabel lblNewLabel = new JLabel("Nombre producto");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(182, 11, 132, 14);
		contentPane.add(lblNewLabel);
		
		buscadorProducto = new JTextField();
		buscadorProducto.setBounds(324, 10, 132, 20);
		contentPane.add(buscadorProducto);
		buscadorProducto.setColumns(10);
		
		buscadorProducto.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filterTablaStock(buscadorProducto.getText(),suc);
			}
			public void removeUpdate(DocumentEvent e) {
				filterTablaStock(buscadorProducto.getText(),suc);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	public JTable getTablaStock() {
		return tablaStock;
	}
	
	private void filterTablaStock(String nombreProducto, int idSucursal) {
		tablaStock.setModel(new StockController().filtrarTablaPorNombre(nombreProducto,idSucursal));
	}
}
