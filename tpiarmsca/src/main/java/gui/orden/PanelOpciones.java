package gui.orden;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Canvas;

public class PanelOpciones extends JPanel {
	private boolean seleccionado;
	private List<String> recorrido;
	private  int diametro = 45;
	private int comienzoY;
	private int espacioEntreVertices = 80;
	private int[] circulosEnX;
	
	public List<String> getRecorrido() {
		return recorrido;
	}

	public PanelOpciones(List<String> recorrido) {
		setPreferredSize(new Dimension(800, 50));
		setBorder(BorderFactory.createLineBorder(new Color(0,64,128)));
		setLayout(null);
		this.recorrido = recorrido;
		comienzoY = 2;
		circulosEnX = new int[recorrido.size()];
		
		for(int i = 0; i<recorrido.size(); i++) {
			circulosEnX[i]= i * (diametro+espacioEntreVertices)+espacioEntreVertices;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
	
		g2d.setColor(Color.orange);
		g2d.setStroke(new BasicStroke(6));
		for(int i = 0; i<recorrido.size()-1;i++) {
			int x1 = circulosEnX[i] + diametro/2;
			int y1 = comienzoY + diametro/2;
			int x2 = circulosEnX[i+1]+diametro/2;
			int y2 = comienzoY + diametro/2;
			
			g2d.drawLine(x1, y1, x2, y2);
		}
		
		for (int i = 0; i < recorrido.size(); i++) {
			int x = circulosEnX[i];
			if(recorrido.get(i).equals("1")) {
				g2d.setColor(Color.orange);
			}else {
				g2d.setColor(new Color(0, 64, 128));
			}
			g2d.fillOval(x, comienzoY, diametro, diametro);
			
			String nombreVertice;
			if(recorrido.get(i).equals("1")) {
				g2d.setColor(Color.black);
				nombreVertice ="P";
			}else {
				g2d.setColor(Color.white);
			    nombreVertice = recorrido.get(i);
			}
			
			FontMetrics fm = g2d.getFontMetrics();
			int textX = x + (diametro - fm.stringWidth(nombreVertice))/2;
			int textY = comienzoY + (diametro - fm.getHeight())/2+fm.getAscent();
			g2d.setFont(new Font("Dialog",Font.BOLD,18));
			g2d.drawString(nombreVertice, textX, textY);
		}
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
		if (seleccionado) {
			setBackground(Color.green);
		} else {
			setBackground(null);
		}
	}

	public boolean estaSeleccionado() {
		return seleccionado;
	}
}

class OpcionListener extends MouseAdapter {
	private PanelOpciones opcionPanel;
	private VentanaCaminosPosibles ventana;

	public OpcionListener(PanelOpciones opcionPanel, VentanaCaminosPosibles ventana) {
		this.opcionPanel = opcionPanel;
		this.ventana = ventana;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		opcionPanel.setSeleccionado(!opcionPanel.estaSeleccionado());
		ventana.setPanelSeleccionado(opcionPanel);
	}
}
