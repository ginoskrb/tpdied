package gui.grafo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class MapaSucursales {
	private Graph<String, DefaultWeightedEdge> mapa;
	private Map<String,Point> posicionesVertices;
	
	public MapaSucursales() {
		mapa = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
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
	
	public List<String> verticesAdyacentes (String nombreVertice){
		ArrayList<String> vertices = new ArrayList<>();
		Set<DefaultWeightedEdge> aristas = mapa.outgoingEdgesOf(nombreVertice);
		for(DefaultWeightedEdge a: aristas) {
			String verticeDestino= mapa.getEdgeTarget(a);
			vertices.add(verticeDestino);
		}
		return vertices;
	}
	
	public List<List<String>> caminos(String v1, String v2){
		List<List<String>> salida = new ArrayList<List<String>>();
		List<String> marcados = new ArrayList<String>();
		marcados.add(v1);
		buscarCaminosAux(v1,v2,marcados,salida);
		return salida;
	}
	
	private void buscarCaminosAux(String v1, String v2, List<String> marcados, List<List<String>> todos) {
		List<String> adyacentes = this.verticesAdyacentes(v1);
		List<String> copiaMarcados = null;
		for(String ady: adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			if(ady.equals(v2)) {
				copiaMarcados.add(v2);
				todos.add(new ArrayList<String>(copiaMarcados));
				}else {
					if(!copiaMarcados.contains(ady)) {
						copiaMarcados.add(ady);
						this.buscarCaminosAux(ady,v2,copiaMarcados,todos);
					}
			}
		}
	}
	
}
