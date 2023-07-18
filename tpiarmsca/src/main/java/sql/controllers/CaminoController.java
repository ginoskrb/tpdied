package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.SucursalModel;
import sql.models.CaminoModel;

@SuppressWarnings("serial")
public class CaminoController {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(CaminoModel.class).buildSessionFactory();

    public void createCamino(SucursalModel sucursalOrigen, SucursalModel sucursalDestino, String tiempoTransito, int capacidadMaxima, boolean estado) {
        try {
            Session session = sessionFactory.openSession();
            CaminoModel camino = new CaminoModel(sucursalOrigen, sucursalDestino, tiempoTransito, capacidadMaxima, estado);
            session.beginTransaction();
            session.save(camino);
            session.getTransaction().commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Camino creado correctamente", "ACEPTADO", JOptionPane.CLOSED_OPTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
	 public void deleteCamino(int id) {
	        try {
	            Session session = sessionFactory.openSession();
	            session.beginTransaction();

	            CaminoModel camino = session.get(CaminoModel.class, id);
	            session.delete(camino);

	            session.getTransaction().commit();
	            session.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
		public void updateCamino(int id, SucursalModel sucursalOrigen, SucursalModel sucursalDestino, String tiempoTransito,
				int capacidadMaxima, boolean estado) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				CaminoModel camino = session.get(CaminoModel.class, id);
				camino.setSucursalOrigen(sucursalOrigen);
				camino.setSucursalDestino(sucursalDestino);
				camino.setTiempoTransito(tiempoTransito);
				camino.setCapacidadMaxima(capacidadMaxima);
				camino.setEstado(estado);

				session.update(camino);
				session.getTransaction().commit();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public DefaultTableModel generadorDeTabla() {
		    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "SUCURSAL ORIGEN", "SUCURSAL DESTINO", "TIEMPO DE TRANSITO", "CAPACIDAD MAXIMA", "ESTADO" }) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    try {
		        Session session = sessionFactory.openSession();
		        session.beginTransaction();
		        List<CaminoModel> resultados = session.createQuery("FROM CaminoModel").list();
		        for (CaminoModel entidad : resultados) {
		            Object[] fila = { entidad.getId(), entidad.getSucursalOrigen().getNombre(), entidad.getSucursalDestino().getNombre(),
		                    entidad.getTiempoTransito(), entidad.getCapacidadMaxima(), entidad.getEstado() };
		            modelo.addRow(fila);
		        }
		        return modelo;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return modelo;
		}
		
		public DefaultTableModel filtrarTablaPorNombre(String nombre) {
		    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
		            new String[] { "ID", "SUCURSAL ORIGEN", "SUCURSAL DESTINO", "TIEMPO DE TRANSITO", "CAPACIDAD MAXIMA", "ESTADO" }) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    try {
		        Session session = sessionFactory.openSession();
		        session.beginTransaction();
		        List<CaminoModel> resultados = session.createQuery("FROM CaminoModel").list();
		        int tamNombre = nombre.length();
		        for (CaminoModel entidad : resultados) {
		            if (entidad.getSucursalOrigen().getNombre().length() >= tamNombre) {
		                if (entidad.getSucursalOrigen().getNombre().substring(0, tamNombre).equals(nombre) || nombre.isEmpty()) {
		                    Object[] fila = { entidad.getId(), entidad.getSucursalOrigen().getNombre(),
		                            entidad.getSucursalDestino().getNombre(), entidad.getTiempoTransito(), entidad.getCapacidadMaxima(),
		                            entidad.getEstado() };
		                    modelo.addRow(fila);
		                }
		            }
		        }
		        return modelo;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return modelo;
		}

		public List<CaminoModel> obtenerTodosLosCaminos() {
		    try {
		        Session session = sessionFactory.openSession();
		        session.beginTransaction();
		        List<CaminoModel> caminos = session.createQuery("FROM CaminoModel", CaminoModel.class).list();
		        session.close();

		        return caminos;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}

		public CaminoModel obtenerCaminoPorId(int id) {
		    try (Session session = sessionFactory.openSession()) {
		        return session.get(CaminoModel.class, id);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}

		public DefaultTableModel editarFilaTablaCamino(int id, int idSucursalOrigen, int idSucursalDestino, String tiempoTransito, int capacidadMaxima, boolean estado) {
		    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
		            new String[] { "ID", "SUCURSAL ORIGEN", "SUCURSAL DESTINO", "TIEMPO DE TRÁNSITO", "CAPACIDAD MÁXIMA", "ESTADO" }) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    try {
		        Session session = sessionFactory.openSession();
		        session.beginTransaction();
		        List<CaminoModel> resultados = session.createQuery("FROM CaminoModel").list();
		        SucursalModel suc_origen = new SucursalController().obtenerSucursalPorId(idSucursalOrigen);
		        SucursalModel suc_destino = new SucursalController().obtenerSucursalPorId(idSucursalDestino);
		        this.updateCamino(id, suc_origen, suc_destino, tiempoTransito, capacidadMaxima, estado);
		        for (CaminoModel entidad : resultados) {
		            Object[] fila = { entidad.getId(), entidad.getSucursalOrigen().getNombre(), entidad.getSucursalDestino().getNombre(),
		                    entidad.getTiempoTransito(), entidad.getCapacidadMaxima(), entidad.getEstado() };
		            modelo.addRow(fila);
		        }
		        return modelo;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return modelo;
		}

		public String getAtributoCamino(int id, String atributo) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				CaminoModel camino = session.get(CaminoModel.class, id);
				
				if (atributo.equals("tiempoTransito")) {
					return camino.getTiempoTransito();
				}
				
				if (atributo == "estado") {
					return Boolean.toString(camino.getEstado());
				}
				session.getTransaction().commit();
				sessionFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "s";
		}

}
