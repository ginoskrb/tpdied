package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import gui.camino.PanelCamino;
import gui.producto.PanelProducto;
import gui.sucursal.PanelSucursal;
import sql.controllers.MapaController;
import sql.controllers.OrdenController;
import gui.grafo.MapaSucursales;
import gui.orden.PanelOrdenProvision;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class App extends JFrame {

	private JPanel contentPane;
	private PanelSucursal panelSuc;
	private	MapaSucursales mapa = new MapaSucursales();
	private PanelOrdenProvision panelOrden;
	private PanelCamino panelCam;
	private PanelProducto panelProd;
	private JPanel panelActual;
	private MapaController map = new MapaController(mapa);
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public App() {
		setTitle("Sistema de Gestión Logistico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//--------------------------------------------//
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1280,720);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 64, 128));
		header.setBounds(0, 0, 1264, 99);
		header.setLayout(new BorderLayout(0, 0));
		getContentPane().add(header);
		
		JLabel Titulo = new JLabel("  SGL");
		Titulo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 66));
		Titulo.setForeground(new Color(255, 255, 255));
		header.add(Titulo, BorderLayout.WEST);
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JPanel menuInicio = new JPanel();
		menuInicio.setBackground(new Color(212, 212, 212));
		menuInicio.setBounds(0, 98, 310, 583);
		getContentPane().add(menuInicio);
		menuInicio.setLayout(new GridLayout(4, 1, 0, 0));
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JPanel cuerpoMenu = this.getPanelOrden();
		contentPane.add(cuerpoMenu);
		this.panelActual = cuerpoMenu;
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JButton botonInicio = new JButton("ORDENES");
		botonInicio.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 31));
		botonInicio.setBackground(new Color(192, 192, 192));
		botonInicio.setForeground(Color.black);
		botonInicio.setFocusPainted(false);
		botonInicio.setBorder(new EmptyBorder(0,0,0,0));
		botonInicio.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				botonInicio.setBackground(new Color(0,64,128));
				botonInicio.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				botonInicio.setBackground(new Color(192, 192, 192));
				botonInicio.setForeground(Color.black);
			}
		});
		botonInicio.addActionListener(e->{
			this.mostrarPanelMenu(getPanelOrden());
		});
		
		menuInicio.add(botonInicio);
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JButton botonSucursales = new JButton("SUCURSALES");
		botonSucursales.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 31));
		botonSucursales.setBackground(new Color(192, 192, 192));
		botonSucursales.setFocusPainted(false);
		botonSucursales.setForeground(Color.black);
		botonSucursales.setBorder(new EmptyBorder(0,0,0,0));
		botonSucursales.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				botonSucursales.setBackground(new Color(0,64,128));
				botonSucursales.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				botonSucursales.setBackground(new Color(192, 192, 192));
				botonSucursales.setForeground(Color.black);
			}
		});
		botonSucursales.addActionListener(e->{
			this.mostrarPanelMenu(getPanelSucursal());
		});
		menuInicio.add(botonSucursales);
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JButton botonCaminos = new JButton("CAMINOS");
		botonCaminos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 31));
		botonCaminos.setBackground(new Color(192, 192, 192));
		botonCaminos.setFocusPainted(false);
		botonCaminos.setForeground(Color.black);
		botonCaminos.setBorder(new EmptyBorder(0,0,0,0));
		botonCaminos.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				botonCaminos.setBackground(new Color(0,64,128));
				botonCaminos.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				botonCaminos.setBackground(new Color(192, 192, 192));
				botonCaminos.setForeground(Color.black);
			}
		});
		botonCaminos.addActionListener(e->{
			this.mostrarPanelMenu(getPanelCamino());
		});
		
		menuInicio.add(botonCaminos);
		//--------------------------------------------//
		
		
		//--------------------------------------------//
		JButton botonProductos = new JButton("PRODUCTOS");
		botonProductos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 31));
		botonProductos.setBackground(new Color(192, 192, 192));
		botonProductos.setFocusPainted(false);
		botonProductos.setBorder(new EmptyBorder(0,0,0,0));
		botonProductos.setForeground(Color.black);
		botonProductos.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				botonProductos.setBackground(new Color(0,64,128));
				botonProductos.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent e) {
				botonProductos.setBackground(new Color(192, 192, 192));
				botonProductos.setForeground(Color.black);
			}
		});
		botonProductos.addActionListener(e->{
			this.mostrarPanelMenu(getPanelProducto());
		});
		menuInicio.add(botonProductos);
		//--------------------------------------------//
		map.crearVertices();
		map.crearAristas();
	}

	private PanelSucursal getPanelSucursal() {
		if(this.panelSuc==null) this.panelSuc = new PanelSucursal(map);
		return  panelSuc;
	}
	
	private PanelOrdenProvision getPanelOrden( ) {
		if(this.panelOrden==null) {
		    this.panelOrden = new PanelOrdenProvision(mapa,new OrdenController().generadorDeTabla());
		}else {
			this.panelOrden.getPto().getTablaOrden().setModel(new OrdenController().generadorDeTabla());
		}
		return panelOrden;
	}
	
	private PanelCamino getPanelCamino() {
		if(this.panelCam==null) this.panelCam = new PanelCamino(map);
		return  panelCam;
	}
	
	private PanelProducto getPanelProducto() {
		if(this.panelProd==null) this.panelProd = new PanelProducto();
		return  panelProd;
	}
	
	private void mostrarPanelMenu (JPanel panel) {
		if(panelActual != null) {
			contentPane.remove(panelActual);
		}
		contentPane.add(panel);
		panelActual = panel;
		contentPane.validate();
		contentPane.repaint();
	}
}
