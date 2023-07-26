package gui.orden;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.controllers.OrdenController;
import sql.controllers.ProductoController;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class OrdenesEnProceso extends JFrame {

	private JPanel contentPane;
	private JTable tablaOrdenes = new JTable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdenesEnProceso frame = new OrdenesEnProceso();
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
	public OrdenesEnProceso() {
		setTitle("ORDENES EN PROCESO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(650,650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 21, 592, 566);
		contentPane.add(scrollPane);
		
		tablaOrdenes.setModel(new OrdenController().generadorDeTablaProcesos());
		tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrdenes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tablaOrdenes.getTableHeader().setBackground(new Color(0,64,128));
		tablaOrdenes.getTableHeader().setForeground(Color.white);
		scrollPane.setViewportView(tablaOrdenes);
	}
}
