package gui.grafo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
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

import sql.controllers.CaminoController;
import sql.controllers.MapaController;
import sql.controllers.StockController;
import sql.controllers.SucursalController;
import sql.models.CaminoModel;

public class MapaSucursales {
	private Graph<String, DefaultWeightedEdge> mapa;
	private Map<String, Point> posicionesVertices;
	private Map<String,Double> capacidadVertices;
	private SucursalController sc = new SucursalController();
	private CaminoController cc = new CaminoController();

	public MapaSucursales() {
		this.mapa = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.posicionesVertices = new HashMap<>();
	}

	public void agregarVertice(String nombreVertice) {
		this.mapa.addVertex(nombreVertice);
	}

	public void eliminarVertice(String nombreVertice) {
		this.mapa.removeVertex(nombreVertice);
	}

	public void eliminarArista(String idOrigen, String idDestino) {
		this.mapa.removeEdge(idOrigen, idDestino);
	}

	public void eliminarTodasLasAristas(Collection listaAristas) {
		this.mapa.removeAllEdges(listaAristas);
	}

	public void agregarArista(String nombreVerticeA, String nombreVerticeB, int peso) {
		this.mapa.addEdge(nombreVerticeA, nombreVerticeB);
		this.mapa.setEdgeWeight(nombreVerticeA, nombreVerticeB, peso);
	}

	public void posicionInicialVertice(String nombreVertice) {
		Random random = new Random();
		int minX = 100;
		int maxX = 336;
		int minY = 100;
		int maxY = 438;
		this.posicionesVertices.put(nombreVertice,
				new Point(random.nextInt(maxX - minX + 1) + minX, random.nextInt(maxY - minY + 1) + minY));
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
	
	public Map<String, Double> getCapacidadVertices() {
		return capacidadVertices;
	}

	public List<String> verticesAdyacentes(String nombreVertice) {
		ArrayList<String> vertices = new ArrayList<>();
		Set<DefaultWeightedEdge> aristas = mapa.outgoingEdgesOf(nombreVertice);
		for (DefaultWeightedEdge a : aristas) {
			String verticeDestino = mapa.getEdgeTarget(a);
			if(sc.getAtributoSucursal(Integer.parseInt(verticeDestino),"estado")=="OPERATIVO") {
				vertices.add(verticeDestino);
			}
		}
		return vertices;
	}
	

	public void generarCapacidadVertices(HashMap<String,Double> cv) {
		this.capacidadVertices =cv;
	}
	
	public List<List<String>> caminos(String v1, String v2, Integer tiempoMaximo, Double pesoOrden) {
		List<List<String>> salida = new ArrayList<List<String>>();
		List<String> marcados = new ArrayList<String>();
		Integer tiempoCamino = 0;
		Double pesoMinimoCamino = 10000.0;
		marcados.add(v1);
		System.out.println("nuevo camino");
		buscarCaminosAux(v1, v2, marcados, salida, tiempoMaximo, tiempoCamino, pesoOrden, pesoMinimoCamino);
		return salida;
	}

	private void buscarCaminosAux(String v1, String v2, List<String> marcados, List<List<String>> todos,
			Integer tiempoMaximo, Integer tiempoCamino, Double pesoOrden, Double pesoMinimoCamino) {
		List<String> adyacentes = this.verticesAdyacentes(v1);
		List<String> copiaMarcados = null;
		for (String ady : adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			CaminoModel camino = cc.obtenerCaminoPorSucursales(Integer.parseInt(v1),Integer.parseInt(ady));
			System.out.println(camino.getEstado());
			if(camino.getEstado()=="OPERATIVO") {
				if (ady.equals(v2)) {
					tiempoCamino += (int) this.mapa.getEdgeWeight(mapa.getEdge(v1, v2));
					if (pesoMinimoCamino >= this.getCapacidadVertices().get(v2)) {
						pesoMinimoCamino = this.getCapacidadVertices().get(v2);
					}
					if (tiempoCamino <= tiempoMaximo && pesoMinimoCamino >= pesoOrden) {
						copiaMarcados.add(v2);
						todos.add(new ArrayList<String>(copiaMarcados));
					}
				} else {
							if (!copiaMarcados.contains(ady)) {
								if (pesoMinimoCamino >= this.getCapacidadVertices().get(ady)) {
									pesoMinimoCamino = this.getCapacidadVertices().get(ady);
								}
								tiempoCamino += (int) this.mapa.getEdgeWeight(mapa.getEdge(v1, ady));
								copiaMarcados.add(ady);
								this.buscarCaminosAux(ady, v2, copiaMarcados, todos, tiempoMaximo, tiempoCamino, pesoOrden,
										pesoMinimoCamino);
							}
						}
					}
				}
			}
}
