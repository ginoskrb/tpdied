package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jgrapht.graph.DefaultEdge;

import gui.grafo.MapaSucursales;
import sql.models.CaminoModel;
import sql.models.SucursalModel;

public class MapaController {
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(SucursalModel.class)
			.buildSessionFactory();
	private MapaSucursales mapa;
	
	public MapaController () {
		
	}
	public MapaController(MapaSucursales mapa) {
		this.mapa = mapa;
	}
	
	public void crearVertices() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel", SucursalModel.class).list();
			for (SucursalModel entidad : resultados) {
				mapa.agregarVertice(""+entidad.getId());
				mapa.posicionInicialVertice(""+entidad.getId());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearAristas() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<CaminoModel> resultados = session.createQuery("FROM CaminoModel", CaminoModel.class).list();
			for (CaminoModel entidad : resultados) {
				mapa.getMapa().addEdge(String.valueOf(entidad.getSucursalOrigen().getId())
						, String.valueOf(entidad.getSucursalDestino().getId()));
				mapa.getMapa().setEdgeWeight(String.valueOf(entidad.getSucursalOrigen().getId()),
						String.valueOf(entidad.getSucursalDestino().getId())
						,Integer.parseInt(entidad.getTiempoTransito()));
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void borrarVertice(String nombreVertice) {
		mapa.eliminarVertice(nombreVertice);
	}
	
	public void borrarArista(int arista) {
		String idOrigen = String.valueOf(new CaminoController().obtenerCaminoPorId(arista).getSucursalOrigen().getId());
		String idDestino = String.valueOf(new CaminoController().obtenerCaminoPorId(arista).getSucursalDestino().getId());
		
		mapa.eliminarArista(idOrigen,idDestino);
	}


	public MapaSucursales getMapa() {
		return mapa;
	}

	
}
