package sql.controllers; //OrdenController

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import sql.models.DetalleOrdenModel;
import sql.models.OrdenModel;
//import sql.models.ProductoModel;
import sql.models.SucursalModel;

public class OrdenController {
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(OrdenModel.class)
			.buildSessionFactory();
	public void createOrden(Timestamp fechaOrden, SucursalModel sucDestino, int tiempoEstimado, String estadoOrden, List<DetalleOrdenModel> detalles) {
		try (Session session = sessionFactory.openSession()) {
			OrdenModel orden = new OrdenModel(fechaOrden, sucDestino, tiempoEstimado, estadoOrden, detalles);
			session.beginTransaction();
			session.save(orden);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Orden generada correctamente", "ACEPTADO",
					JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
