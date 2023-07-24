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
import javax.swing.border.EmptyBorder;

import gui.grafo.MapaSucursales;

public class VentanaCaminosPosibles extends JFrame {

	private JPanel contentPane;
	private PanelOpciones panelSeleccionado = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, MapaSucursales mapa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCaminosPosibles frame = new VentanaCaminosPosibles(mapa);
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
	public VentanaCaminosPosibles(MapaSucursales mapa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Recorridos");
		
		List<List<String>> recorridos = mapa.caminos("6", "7");
		List<PanelOpciones> opciones = new ArrayList<PanelOpciones>();
		PanelOpciones op;
		
		for(int i = 0; i<recorridos.size(); i++) {
			op = new PanelOpciones(recorridos.get(i));
			op.addMouseListener(new OpcionListener(op,this));
			opciones.add(op);
		}
		
		JPanel contenedorDeOpciones = new JPanel();
		contenedorDeOpciones.setLayout(new BoxLayout(contenedorDeOpciones, BoxLayout.Y_AXIS));
		for(int i = 0; i<opciones.size();i++) {
			contenedorDeOpciones.add(opciones.get(i));
		}
		
		JButton asignar = new JButton("Seleccionar");
		asignar.setBackground(new Color(0, 64, 128));
		asignar.setForeground(new Color(255, 255, 255));
		asignar.setFont(new Font("Dialog", Font.BOLD, 15));
		asignar.setFocusPainted(false);
		asignar.addActionListener(e->{
			System.out.println(this.panelSeleccionado.getRecorrido());
		});
		contenedorDeOpciones.add(asignar);
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
