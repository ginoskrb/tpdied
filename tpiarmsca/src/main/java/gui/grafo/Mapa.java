package gui.grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Mapa extends JPanel {

	private String verticeSeleccionado;
	private Point desplazamientoMouse;
	private MapaSucursales mapa;
	
	public Mapa(MapaSucursales mapa) {
		this.mapa = mapa;
		setBackground(new Color(192, 192, 192));
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setBounds(25, 22, 436, 538);
	
		
		addMouseListener(new MouseAdapter() { //Detectamos los movimientos de click con el mouse
			@Override
			public void mousePressed(MouseEvent e) {
				verticeSeleccionado = buscarVerticeEnElPunto(e.getPoint());
				if(verticeSeleccionado!=null) {
					desplazamientoMouse = new Point(e.getX() - mapa.getPosicionesVertices().get(verticeSeleccionado).x,
																			e.getY() - mapa.getPosicionesVertices().get(verticeSeleccionado).y);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				verticeSeleccionado = null;
				desplazamientoMouse = null;
			}
		});
		
		addMouseMotionListener(new MouseAdapter() { //Detectamos el desplazamiento del mouse
			@Override
			public void mouseDragged(MouseEvent e) {
				if(verticeSeleccionado!=null) {
					mapa.getPosicionesVertices().put(verticeSeleccionado, new Point(e.getX() - desplazamientoMouse.x, e.getY() - desplazamientoMouse.y));
					repaint();
				}
			}
		});
	}
	
	private String buscarVerticeEnElPunto(Point p) {
		for(String vertice: mapa.getMapa().vertexSet()) {
			Point posicionVertice = mapa.getPosicionesVertices().get(vertice);
			Rectangle areaDelVertice = new Rectangle(posicionVertice.x-10, posicionVertice.y-10,60,60);
			if(areaDelVertice.contains(p)) {
				return vertice;
			}
		}
		return null;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D grafico2d = (Graphics2D) g;
			
		for(DefaultWeightedEdge arista: mapa.getMapa().edgeSet()) {
			String origen = mapa.getMapa().getEdgeSource(arista);
			String destino = mapa.getMapa().getEdgeTarget(arista);
			
			Point origenPosicion = mapa.getPosicionesVertices().get(origen);
			Point destinoPosicion = mapa.getPosicionesVertices().get(destino);
			grafico2d.setStroke(new BasicStroke(3));
			drawArrow(grafico2d,origenPosicion.x, origenPosicion.y, destinoPosicion.x, destinoPosicion.y-5);
		}
		
		for(String vertice: mapa.getMapa().vertexSet()) {
			Point posicionDelVertice = mapa.getPosicionesVertices().get(vertice);
			
			if(vertice.equals("C") || vertice.equals("P")) {
				grafico2d.setColor(Color.orange);
			}else {
				grafico2d.setColor(new Color(0,64,128));
			}
			
			grafico2d.fillOval(posicionDelVertice.x-10,posicionDelVertice.y-10, 35, 35);
			
			if(vertice.equals("C") || vertice.equals("P")) {
				grafico2d.setColor(Color.BLACK);
			}else {
				grafico2d.setColor(Color.WHITE);
			}
			grafico2d.setFont(new Font("Dialog", Font.PLAIN, 18));
			grafico2d.drawString(vertice, posicionDelVertice.x-2, posicionDelVertice.y+15);
		}
	}
	
	private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
	    int arrowSize = 10;

	    double dx = x2 - x1;
	    double dy = y2 - y1;
	    double angle = Math.atan2(dy, dx);
	    int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
	    int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
	    int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
	    int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));

	    g2d.drawLine(x1, y1, x2, y2);

	    // Define la forma de la flecha utilizando GeneralPath
	    GeneralPath path = new GeneralPath();
	    path.moveTo(x2, y2);
	    path.lineTo(x3, y3);
	    path.lineTo(x4, y4);
	    path.closePath();

	    // Dibuja la flecha
	    g2d.fill(path);
	}

}
