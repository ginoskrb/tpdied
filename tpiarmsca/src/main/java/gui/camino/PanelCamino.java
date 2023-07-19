package gui.camino;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sql.controllers.CaminoController;

@SuppressWarnings("serial")
public class PanelCamino extends JPanel {
	private JTextField Buscador;
	private JTable tablaCaminos = new JTable();

	/**
	 * Create the panel.
	 */
	public PanelCamino() {
		setBounds(309, 98, 955, 583);
		setLayout(null);

		// Buscador
		Buscador = new JTextField();
		Buscador.setBounds(275, 79, 624, 27);
		add(Buscador);
		Buscador.setColumns(10);

		Buscador.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		// Etiqueta de búsqueda
		JLabel lblNewLabel = new JLabel("Buscar camino (Origen):");
		lblNewLabel.setForeground(new Color(0, 64, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(275, 54, 257, 14);
		add(lblNewLabel);

		// Tabla de caminos
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 129, 624, 299);
		add(scrollPane);
		tablaCaminos = new JTable();
		tablaCaminos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablaCaminos.setModel(new CaminoController().generadorDeTabla());
		tablaCaminos.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaCaminos.getColumnModel().getColumn(3).setPreferredWidth(99);
		tablaCaminos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCaminos.getTableHeader().setBackground(new Color(0, 64, 128));
		tablaCaminos.getTableHeader().setForeground(Color.white);
		scrollPane.setViewportView(tablaCaminos);

		// Botón Agregar
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setForeground(new Color(255, 255, 255));
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(new Color(0, 64, 128));
		botonAgregar.setBounds(74, 164, 158, 51);
		botonAgregar.setFocusPainted(false);
		botonAgregar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonAgregar.addActionListener(e -> {
			AgregarCamino.main(null, this);
		});
		add(botonAgregar);

		// Botón Eliminar
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setForeground(new Color(255, 255, 255));
		botonEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEliminar.setBackground(new Color(0, 64, 128));
		botonEliminar.setBounds(74, 243, 158, 51);
		botonEliminar.setFocusPainted(false);
		botonEliminar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaCaminos.getSelectedRow();
				if (filaSeleccionada != -1) {
					Object idSeleccionado = tablaCaminos.getValueAt(filaSeleccionada, 0);
					int op = JOptionPane.showOptionDialog(null, "Estas seguro que deseas eliminar este camino?",
							"Avertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "Si", "No" }, "Si");
					switch (op) {
					case JOptionPane.YES_OPTION:
						new CaminoController().deleteCamino(Integer.parseInt(idSeleccionado.toString()));
						tablaCaminos.setModel(new CaminoController().generadorDeTabla());
						break;
					case JOptionPane.NO_OPTION:
						break;
					}
				}
			}
		});
		add(botonEliminar);

		// Botón Editar
		JButton botonEditar = new JButton("Editar");
		botonEditar.setForeground(new Color(255, 255, 255));
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(new Color(0, 64, 128));
		botonEditar.setBounds(74, 325, 158, 51);
		botonEditar.setFocusPainted(false);
		botonEditar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEditar.addActionListener(e -> {
			int filaSeleccionada = tablaCaminos.getSelectedRow();
			if (filaSeleccionada != -1) {
				Object idSeleccionado = tablaCaminos.getValueAt(filaSeleccionada, 0);
				EditarCamino.main(null, this, Integer.parseInt(idSeleccionado.toString()));
			}
		});
		add(botonEditar);
	}

	private void filterTable() {
		tablaCaminos.setModel(new CaminoController().filtrarTablaPorNombre(Buscador.getText()));
	}

	public JTable getTablaCaminos() {
		return tablaCaminos;
	}

	public void setTablaCaminos(JTable tablaCaminos) {
		this.tablaCaminos = tablaCaminos;
	}

}