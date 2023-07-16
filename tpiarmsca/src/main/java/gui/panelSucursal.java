package gui;

import sql.controllers.SucursalController;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
@SuppressWarnings("serial")
public class panelSucursal extends JPanel {
	private JTextField Buscador;
	private JTable tablaSucursales = new JTable();
	private agregarSucursal ventanaAgregarSucursal = new agregarSucursal(this);
	private editarSucursal ventanaEditarSucursal;
	private SucursalController suc = new SucursalController();

	/**
	 * CCreate the panel.
	 */
	public panelSucursal() {
		setBounds(309, 98, 955, 583);
		setLayout(null);

		// ---------------------------------------------------------------//
		Buscador = new JTextField();
		Buscador.setBounds(151, 76, 582, 27);
		add(Buscador);
		Buscador.setColumns(10);
		// ---------------------------------------------------------------//

		// ---------------------------------------------------------------//
		JButton botonBuscar = new JButton("");
		botonBuscar.setIcon(new ImageIcon("C:\\Users\\Franco\\Downloads\\buscar (1).png"));
		botonBuscar.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		botonBuscar.setBackground(new Color(240, 240, 240));
		botonBuscar.setBounds(750, 69, 37, 34);
		botonBuscar.setFocusPainted(false);
		botonBuscar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonBuscar.addActionListener(e -> {
			tablaSucursales.setModel(new SucursalController().filtrarTablaPorNombre(Buscador.getText()));
		});
		add(botonBuscar);

		JLabel lblNewLabel = new JLabel("Buscar sucursal (Nombre sucursal)");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 52, 257, 14);
		add(lblNewLabel);

		// ---------------------------------------------------------------//

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 125, 624, 299);
		add(scrollPane);
		tablaSucursales.setModel(new SucursalController().generadorDeTabla());
		tablaSucursales.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaSucursales.getColumnModel().getColumn(3).setPreferredWidth(99);
		tablaSucursales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaSucursales);

		// ---------------------------------------------------------------//
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(new Color(195, 207, 217));
		botonAgregar.setBounds(167, 454, 143, 39);
		botonAgregar.setFocusPainted(false);
		botonAgregar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonAgregar.addActionListener(e -> {
			ventanaAgregarSucursal.main(null, this);
		});
		add(botonAgregar);
		// ---------------------------------------------------------------//

		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEliminar.setBackground(new Color(195, 207, 217));
		botonEliminar.setBounds(357, 454, 143, 39);
		botonEliminar.setFocusPainted(false);
		botonEliminar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaSucursales.getSelectedRow();
				if (filaSeleccionada != -1) {
					
                    Object idSeleccionado = tablaSucursales.getValueAt(filaSeleccionada, 0);
                    int op = JOptionPane.showOptionDialog(null,"Estas seguro que deseas eliminar esta sucursal?","Avertencia",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] {"Si","No"},"Si");
                    switch(op) {
                    	case JOptionPane.YES_OPTION: 
                    		suc.deleteSucursal(Integer.parseInt(idSeleccionado.toString()));
                    		tablaSucursales.setModel(new SucursalController().generadorDeTabla());
                    		break;
                    	case JOptionPane.NO_OPTION: 
                    		break;
                    }
                } 
            }
		});
		add(botonEliminar);

		// ---------------------------------------------------------------//
		
		// ---------------------------------------------------------------//
		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(new Color(195, 207, 217));
		botonEditar.setBounds(632, 454, 143, 39);
		botonEditar.setFocusPainted(false);
		botonEditar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEditar.addActionListener(e->{
			int filaSeleccionada = tablaSucursales.getSelectedRow();
			if (filaSeleccionada != -1) {
				Object idSeleccionado = tablaSucursales.getValueAt(filaSeleccionada, 0);
				ventanaEditarSucursal.main(null, this, Integer.parseInt(idSeleccionado.toString()));
			}
		});
		add(botonEditar);
		// ---------------------------------------------------------------//
		
	}

	public JTable getTablaSucursales() {
		return tablaSucursales;
	}

	public void setTablaSucursales(JTable tablaSucursales) {
		this.tablaSucursales = tablaSucursales;
	}

}
