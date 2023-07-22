package gui.orden; //GenerarOrden

import java.awt.EventQueue;
import java.awt.Font;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.text.SimpleDateFormat;
import sql.controllers.OrdenController;
import sql.controllers.ProductoController;
import sql.models.DetalleOrdenModel;
import sql.models.OrdenModel;
import sql.models.ProductoModel;
import sql.models.SucursalModel;
import java.sql.Timestamp;
import java.awt.Color;
@SuppressWarnings("serial")
public class GenerarOrden extends JFrame {

	private JPanel contentPane;
	private JTextField textTiempo;
	private JTable tablaProductosDis = new JTable();
	private JTable tablaProductosReq = new JTable();
	private List<ProductoCantidad> productosRequeridos = new ArrayList<ProductoCantidad>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, SucursalModel sucDestino) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarOrden frame = new GenerarOrden(sucDestino);
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
	public GenerarOrden(SucursalModel sucDestino) {
		setTitle("Generar Orden");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024,576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        MaskFormatter dateFormatter = null;
		try {
			dateFormatter = new MaskFormatter("##/##/####");
			dateFormatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFormattedTextField fechaTextField = new JFormattedTextField(dateFormatter);
		fechaTextField.setBounds(63, 11, 86, 20);
	    fechaTextField.setFocusLostBehavior(JFormattedTextField.COMMIT); 
	    fechaTextField.setValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		contentPane.add(fechaTextField);
		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFecha.setBounds(10, 11, 60, 17);
		contentPane.add(lblFecha);
		
		textTiempo = new JTextField();
		textTiempo.setBounds(378, 11, 74, 20);
		contentPane.add(textTiempo);
		textTiempo.setColumns(10);
		
		JLabel lblTiempo = new JLabel("Tiempo estimado:");
		lblTiempo.setForeground(new Color(255, 255, 255));
		lblTiempo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTiempo.setBounds(241, 11, 127, 17);
		contentPane.add(lblTiempo);
		
		JScrollPane scrollListaProductosReq = new JScrollPane();
		scrollListaProductosReq.setBounds(10, 80, 320, 390);
		contentPane.add(scrollListaProductosReq);
		scrollListaProductosReq.setViewportView(tablaProductosReq);
		
		JScrollPane scrollListaProductosDis = new JScrollPane();
		scrollListaProductosDis.setBounds(538, 80, 460, 390);
		contentPane.add(scrollListaProductosDis);
		tablaProductosDis.setModel(new ProductoController().generadorDeTabla());
		tablaProductosDis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProductosDis.getTableHeader().setBackground(new Color(0,64,128));
		tablaProductosDis.getTableHeader().setForeground(Color.white);
		scrollListaProductosDis.setViewportView(tablaProductosDis);
		
		
		
		
		JLabel lblListaProductosReq = new JLabel("Lista de productos requeridos:");
		lblListaProductosReq.setForeground(new Color(255, 255, 255));
		lblListaProductosReq.setFont(new Font("Dialog", Font.BOLD, 15));
		lblListaProductosReq.setBounds(10, 52, 226, 20);
		contentPane.add(lblListaProductosReq);
		
		JButton btnAgregar = new JButton("<- Agregar a la lista");
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAgregar.setBounds(350, 443, 167, 27);
		btnAgregar.addActionListener(e -> {
			int filaSeleccionada = tablaProductosDis.getSelectedRow();
			if (filaSeleccionada != -1) {
				Object idSeleccionado = tablaProductosDis.getValueAt(filaSeleccionada, 0);
				ProductoModel prodSeleccionado = new ProductoController().obtenerProductoPorId(Integer.parseInt(idSeleccionado.toString()));
				String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad requerida:", prodSeleccionado.getNombre(), JOptionPane.QUESTION_MESSAGE);
				ProductoCantidad prodcant = new ProductoCantidad(prodSeleccionado,Integer.parseInt(input));
				productosRequeridos.add(prodcant);
				tablaProductosReq.setModel(this.generadorDeTabla(productosRequeridos));
				tablaProductosReq.getTableHeader().setBackground(new Color(0,64,128));
				tablaProductosReq.getTableHeader().setForeground(Color.white);

			}
		});
		contentPane.add(btnAgregar);
		
		JLabel lblListaProductosDis = new JLabel("Lista de productos disponibles:");
		lblListaProductosDis.setForeground(new Color(255, 255, 255));
		lblListaProductosDis.setBackground(new Color(255, 255, 255));
		lblListaProductosDis.setFont(new Font("Dialog", Font.BOLD, 15));
		lblListaProductosDis.setBounds(538, 52, 226, 20);
		contentPane.add(lblListaProductosDis);
		
		JButton btnGenerarOrden = new JButton("GENERAR ORDEN");
		btnGenerarOrden.setBackground(new Color(255, 255, 255));
		btnGenerarOrden.setFont(new Font("Dialog", Font.BOLD, 14));
		btnGenerarOrden.setBounds(831, 499, 167, 27);
		btnGenerarOrden.addActionListener(e -> {
			String fechaTexto = fechaTextField.getText();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date fecha = formatoFecha.parse(fechaTexto);
				Timestamp fechaOrden = new Timestamp(fecha.getTime());
				OrdenModel orden_prov = new OrdenController().createOrden(fechaOrden, sucDestino, Integer.parseInt(textTiempo.getText()) , "PENDIENTE");
				for(ProductoCantidad prodcant: productosRequeridos) {
					orden_prov.addDetalle(new DetalleOrdenModel(orden_prov,prodcant.getProducto(),prodcant.getCantidad()));
				}
				new OrdenController().updateOrden(orden_prov);
				dispose();
			}catch (Exception s) {
				s.printStackTrace();
			}
			
		});
		contentPane.add(btnGenerarOrden);
	}
	
	private DefaultTableModel generadorDeTabla(List<ProductoCantidad> productosRequeridos) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "CANTIDAD REQUERIDA" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (ProductoCantidad prodCant: productosRequeridos) {
			Object[] fila = { prodCant.getProducto().getId(), prodCant.getProducto().getNombre(), prodCant.getCantidad() };
			modelo.addRow(fila);
		}
		
		return modelo;
	}
}
