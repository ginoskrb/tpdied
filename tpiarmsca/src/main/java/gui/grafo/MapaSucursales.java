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
	private Map<String, Double> capacidadVertices;
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
			if (verticeDestino.equals("C")) {
				vertices.add(verticeDestino);
			} else if (sc.getAtributoSucursal(Integer.parseInt(verticeDestino), "estado") == "OPERATIVO") {
				vertices.add(verticeDestino);
			}
		}
		return vertices;
	}

	public void generarCapacidadVertices(HashMap<String, Double> cv) {
		this.capacidadVertices = cv;
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
		String vAux;
		List<String> adyacentes;
		if (sc.getAtributoSucursal(Integer.parseInt(v1), "nombre").equals("PUERTO")) {
			adyacentes = this.verticesAdyacentes("P");
		} else {
			adyacentes = this.verticesAdyacentes(v1);
		}
		List<String> copiaMarcados = null;
		for (String ady : adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			if (!ady.equals("C")) {
				CaminoModel camino = cc.obtenerCaminoPorSucursales(Integer.parseInt(v1), Integer.parseInt(ady));
				if (camino.getEstado() == "OPERATIVO") {
					if (sc.getAtributoSucursal(Integer.parseInt(v1), "nombre").equals("PUERTO")) {
						vAux = "P";
					} else {
						vAux = v1;
					}
					if (ady.equals(v2)) {
						tiempoCamino += (int) this.mapa.getEdgeWeight(mapa.getEdge(vAux, v2));
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
							tiempoCamino += (int) this.mapa.getEdgeWeight(mapa.getEdge(vAux, ady));
							copiaMarcados.add(ady);
							this.buscarCaminosAux(ady, v2, copiaMarcados, todos, tiempoMaximo, tiempoCamino, pesoOrden,
									pesoMinimoCamino);
						}
					}
				}
			}
		}
	}

	
	public void puertoACentral() {
		List<List<String>> salida = new ArrayList<List<String>>();
		List<String> marcados = new ArrayList<String>();
		marcados.add("P");
		puertoACentralAux("P","4",marcados,salida);
		System.out.println(salida);
	}
	
	public void puertoACentralAux(String v1,String v2, List<String> marcados, List<List<String>> salida) {
		List<String> adyacentes = this.verticesAdyacentes(v1);
		List<String> copiaMarcados = null;
		for(String ady: adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			if(ady.equals("C")) {
				copiaMarcados.add("C");
				salida.add(new ArrayList<String>(copiaMarcados));
			} else {
				if(!copiaMarcados.contains(ady)) {
					copiaMarcados.add(ady);
					this.puertoACentralAux(ady, v2, copiaMarcados, salida);
				}
			}
		}
	}
	
	public List<List<String>> adyacentesConPeso(String v1,List<Camino> caminos) {
		List<List<String>> lista = new ArrayList<>();
		List<String> adyacentes;
		if(v1.equals("2")) {
			adyacentes = this.verticesAdyacentes("P");
		}else {
			adyacentes = this.verticesAdyacentes(v1);
		}
		
		for(String ady: adyacentes) {
			ArrayList<String> adyacentePeso = new ArrayList<String>();
			adyacentePeso.add(ady);
			if(ady.equals("C")){
				adyacentePeso.add(this.pesoDeUnCamino(v1, "4",caminos));
			}else {
				adyacentePeso.add(this.pesoDeUnCamino(v1, ady,caminos));
			}
			lista.add(adyacentePeso);
		}
		return lista;
	}
	
	public void flujoMaximo() {
		List<Camino> listCaminos = this.caminos();
		Integer total = 0;
		boolean flag =  false;
		try{
			do {
				total += caminoMasPesado(listCaminos);
			}while (true);
		} catch (Exception e) {
			System.out.println(total);
		}
	}
	
	
	public Integer caminoMasPesado(List<Camino> caminos) {
		List<String> lista = new ArrayList<>();
		lista.add("P");
		Integer pesoMaximo = caminoMasPesadoAux("2",lista,16000,caminos);
		this.actualizarPesos(lista,pesoMaximo,caminos);
		System.out.println(lista + " " + pesoMaximo);
		for(Camino x: caminos) {
			System.out.println(x.getOrigen() + " " + x.getDestino() + " " + x.getPeso());
		}
		return pesoMaximo;
	}
	
	public Integer caminoMasPesadoAux(String v ,List<String> resultado,Integer pesoMaximo,List<Camino> caminos){
		if(!v.equals("C")) {
			Integer max = 0;
			String nodo = "";
			List<List<String>> adyacentes = this.adyacentesConPeso(v,caminos);
			for(List<String> x: adyacentes) {
				if(Integer.parseInt(x.get(1))>max && Integer.parseInt(x.get(1))!=0) {
					max = Integer.parseInt(x.get(1));
					nodo = x.get(0);
				}
			}
			if(max==0) {
				actualizarPesos(resultado.subList(resultado.size()-2, resultado.size())
						,Integer.parseInt(this.pesoDeUnCamino(resultado.get(resultado.size()-2),resultado.get(resultado.size()-1), caminos)),caminos);
				return Integer.parseInt(this.pesoDeUnCamino(resultado.get(resultado.size()-2),resultado.get(resultado.size()-1), caminos));
			}else if(pesoMaximo>max) {
				pesoMaximo = max;
			}
			resultado.add(nodo);
			return caminoMasPesadoAux(nodo,resultado,pesoMaximo,caminos);
		}else {
			return pesoMaximo;
		}
	}
	
	
	public void actualizarPesos(List<String> ruta, Integer pesoMaximo, List<Camino> caminos) {
		for(int i = 0 ; i<ruta.size()-1; i++) {
			for(Camino x: caminos) {
				if(ruta.get(i).equals("P")) {
					if(x.getOrigen().equals("2") && x.getDestino().equals(ruta.get(i+1))) {
						System.out.println(pesoMaximo);
						x.setPeso(String.valueOf(Integer.parseInt(x.getPeso())-pesoMaximo));
						break;
					}
				}else if(ruta.get(i+1).equals("C")) {
					if(x.getOrigen().equals(ruta.get(i)) && x.getDestino().equals("4")) {
						x.setPeso(String.valueOf(Integer.parseInt(x.getPeso())-pesoMaximo));
						break;
					}
				} else {
					if(x.getOrigen().equals(ruta.get(i)) && x.getDestino().equals(ruta.get(i+1))) {
						x.setPeso(String.valueOf(Integer.parseInt(x.getPeso())-pesoMaximo));
						break;
					}
				}
			}
		}
		
	}
	
	
	public List<Camino> caminos(){
		ArrayList<Camino> listaCaminos = new ArrayList<>();
		for(CaminoModel x: cc.obtenerTodosLosCaminos()) {
			listaCaminos.add(new Camino(String.valueOf(x.getSucursalOrigen().getId())
					,String.valueOf(x.getSucursalDestino().getId())
					,String.valueOf(x.getCapacidadMaxima())));
		}
		return listaCaminos;
	}
	
	public String pesoDeUnCamino(String origen, String Destino, List<Camino>caminos) {
		if(origen.equals("P")) {
			origen = "2";
		}
		for(Camino x: caminos) {
			if(x.getOrigen().equals(origen) && x.getDestino().equals(Destino)) return x.getPeso();
		}
		return null;
	}

	

}
