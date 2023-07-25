package sql.controllers; //OrdenController

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.persistence.TypedQuery;
import sql.models.CaminoModel;
import sql.models.DetalleOrdenModel;
import sql.models.OrdenModel;
import sql.models.ProductoModel;
//import sql.models.ProductoModel;
import sql.models.SucursalModel;


public class OrdenController {
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(OrdenModel.class)
			.buildSessionFactory();
	public OrdenModel createOrden(Timestamp fechaOrden, SucursalModel sucDestino, int tiempoEstimado, String estadoOrden) {
		OrdenModel orden = new OrdenModel(fechaOrden, sucDestino, tiempoEstimado, estadoOrden);
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(orden);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Orden generada correctamente", "ACEPTADO",
					JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return orden;
	}
	
	public void updateOrden(OrdenModel orden) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			if (orden != null) {
				session.update(orden);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<OrdenModel> obtenerTodasLasOrdenes() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<OrdenModel> ordenes = session.createQuery("FROM OrdenModel", OrdenModel.class).list();
			return ordenes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public OrdenModel obtenerOrdenPorId(int id) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(OrdenModel.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public DefaultTableModel generadorDeTabla() {
	    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "FECHA", "DESTINO", "TIEMPO MAXIMO", "ESTADO" }) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    try {
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	        List<OrdenModel> resultados = session.createQuery("FROM OrdenModel").list();
	        for (OrdenModel entidad : resultados) {
	            Object[] fila = { entidad.getId(), entidad.getFechaOrden(), entidad.getSucursalDestino().getNombre(), entidad.getTiempoMaximo(),
	                    entidad.getEstadoOrden() };
	            modelo.addRow(fila);
	        }
	        return modelo;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return modelo;
	}
	
	public List<DetalleOrdenModel> obtenerProductosSolicitados(int idOrden) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM DetalleOrdenModel WHERE ordenProvision.id = :idOrden";
            TypedQuery<DetalleOrdenModel> query = session.createQuery(hql, DetalleOrdenModel.class);
            query.setParameter("idOrden", idOrden);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
	
	public double obtenerPesoTotalDeOrden(int idOrden) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT SUM(d.cantidad_requerida * prod.peso_kg) " +
                         "FROM detalle_orden_provision d " +
                         "JOIN producto prod ON d.id_producto = prod.id " +
                         "WHERE d.id_orden_provision = :idOrden";

            Query<Double> query = session.createNativeQuery(sql, Double.class);
            query.setParameter("idOrden", idOrden);

            Double pesoTotal = query.uniqueResult();
            return pesoTotal != null ? pesoTotal : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
	
	
	
}
