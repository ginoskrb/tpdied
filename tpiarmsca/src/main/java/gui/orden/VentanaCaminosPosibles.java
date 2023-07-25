package gui.orden;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import gui.grafo.MapaSucursales;
import sql.controllers.OrdenController;
import sql.models.OrdenModel;

public class VentanaCaminosPosibles extends JFrame {

	private JPanel contentPane;
	private PanelOpciones panelSeleccionado = null;
	private ArrayList<String> origenes;
	private String destino;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, MapaSucursales mapa,String destino, ArrayList<String> origenes,Integer tiempoMaximo, Integer idOrden, JTable tablaOrden) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCaminosPosibles frame = new VentanaCaminosPosibles(mapa,destino,origenes,tiempoMaximo,idOrden,tablaOrden);
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
	public VentanaCaminosPosibles(MapaSucursales mapa, String destino, ArrayList<String> origenes,Integer tiempoMaximo,Integer idOrden, JTable tablaOrden) {
		this.origenes = origenes;
		this.destino = destino;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Recorridos");
		
		
		Double pesoOrden = new OrdenController().obtenerPesoTotalDeOrden(idOrden);
		System.out.println(pesoOrden);
		List<PanelOpciones> opciones = new ArrayList<PanelOpciones>();
		PanelOpciones op;
		for(int i = 0; i<origenes.size();i++) {
			List<List<String>> recorridos = mapa.caminos(origenes.get(i), destino,tiempoMaximo,pesoOrden);
			for(int j = 0; j<recorridos.size(); j++) {
				op = new PanelOpciones(recorridos.get(j));
				op.addMouseListener(new OpcionListener(op,this));
				opciones.add(op);
			}
		}
	
		JPanel contenedorDeOpciones = new JPanel();
		contenedorDeOpciones.setLayout(new BoxLayout(contenedorDeOpciones, BoxLayout.Y_AXIS));
		for(int i = 0; i<opciones.size();i++) {
			contenedorDeOpciones.add(opciones.get(i));
		}
		
		JButton seleccionar = new JButton("Seleccionar");
		seleccionar.setBackground(new Color(0, 64, 128));
		seleccionar.setForeground(new Color(255, 255, 255));
		seleccionar.setFont(new Font("Dialog", Font.BOLD, 15));
		seleccionar.setFocusPainted(false);
		seleccionar.addActionListener(e->{
			OrdenController odc = new OrdenController();
			OrdenModel odm = odc.obtenerOrdenPorId(idOrden);
			odm.setEstadoOrden("EN PROCESO");
			odc.updateOrden(odm);
			tablaOrden.setModel(odc.generadorDeTabla());
			dispose();
		});
		contenedorDeOpciones.add(seleccionar);
		getContentPane().add(contenedorDeOpciones);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	public void setPanelSeleccionado(PanelOpciones panel) {
		if(panelSeleccionado != null && panelSeleccionado != panel) {
			panelSeleccionado.setSeleccionado(false);
		}
		panelSeleccionado = panel;
	}
	
	

}
