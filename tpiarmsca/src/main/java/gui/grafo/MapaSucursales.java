package gui.grafo;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class MapaSucursales {
	private Graph<String, DefaultEdge>mapa;
	private Map<String,Point> posicionesVertices;
	
	public MapaSucursales() {
		mapa = new SimpleGraph<>(DefaultEdge.class);
		posicionesVertices = new HashMap<>();
	}
	
	public void agregarVertice(String nombreVertice) {
		this.mapa.addVertex(nombreVertice);
	}
	
	public void eliminarVertice(String nombreVertice) {
		this.mapa.removeVertex(nombreVertice);
	}
	
	public void eliminarArista(String idOrigen, String idDestino) {
		this.mapa.removeEdge(idOrigen,idDestino);
	}
	
	public void agregarArista(String nombreVerticeA, String nombreVerticeB) {
		this.mapa.addEdge(nombreVerticeA, nombreVerticeB);
	}
	
	public void posicionInicialVertice(String nombreVertice) {
		Random random = new Random();
		int minX = 100;
		int maxX =336;
		int minY = 100;
		int maxY = 438;
		this.posicionesVertices.put(nombreVertice, new Point(random.nextInt(maxX-minX+1)+minX,random.nextInt(maxY-minY+1)+minY));
	}

	public Graph<String, DefaultEdge> getMapa() {
		return mapa;
	}

	public void setMapa(Graph<String, DefaultEdge> mapa) {
		this.mapa = mapa;
	}

	public Map<String, Point> getPosicionesVertices() {
		return posicionesVertices;
	}

	public void setPosicionesVertices(Map<String, Point> posicionesVertices) {
		this.posicionesVertices = posicionesVertices;
	}
	
	
}
