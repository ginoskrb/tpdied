package gui.grafo;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class MapaSucursales {
	private Graph<String, DefaultWeightedEdge> mapa;
	private Map<String,Point> posicionesVertices;
	
	public MapaSucursales() {
		mapa = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
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
	
	public void agregarArista(String nombreVerticeA, String nombreVerticeB, int peso) {
		this.mapa.addEdge(nombreVerticeA, nombreVerticeB);
		this.mapa.setEdgeWeight(nombreVerticeA, nombreVerticeB, peso);
	}
	
	public void posicionInicialVertice(String nombreVertice) {
		Random random = new Random();
		int minX = 100;
		int maxX =336;
		int minY = 100;
		int maxY = 438;
		this.posicionesVertices.put(nombreVertice, new Point(random.nextInt(maxX-minX+1)+minX,random.nextInt(maxY-minY+1)+minY));
	}

	public Graph<String, DefaultWeightedEdge> getMapa() {
		return mapa;
	}

	public void setMapa(Graph<String, DefaultWeightedEdge> mapa) {
		this.mapa = mapa;
	}

	public Map<String, Point> getPosicionesVertices() {
		return posicionesVertices;
	}

	public void setPosicionesVertices(Map<String, Point> posicionesVertices) {
		this.posicionesVertices = posicionesVertices;
	}
	
	
}
