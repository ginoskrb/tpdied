package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import gui.grafo.MapaSucursales;
import sql.models.SucursalModel;

@SuppressWarnings("serial")
public class SucursalController {
	
	private static final SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(SucursalModel.class)
			.buildSessionFactory();

	public void createSucursal(String nombre, String hapertura, String hcierre, boolean estado) {
		try (Session session = sessionFactory.openSession()) {
			SucursalModel sucursal = new SucursalModel(nombre, hapertura, hcierre, estado);
			session.beginTransaction();
			session.save(sucursal);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Sucursal creada correctamente", "ACEPTADO", JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteSucursal(int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if (sucursal != null) {
				session.delete(sucursal);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSucursal(int id, String[] valores) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if (sucursal != null) {
				sucursal.setNombre(valores[0]);
				sucursal.setHapertura(valores[1]);
				sucursal.setHcierre(valores[2]);
				sucursal.setEstado(Boolean.parseBoolean(valores[3]));
				session.update(sucursal);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAtributoSucursal(int id, String atributo) {
		try (Session session = sessionFactory.openSession()) {
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if (sucursal != null) {
				if (atributo.equals("nombre")) {
					return sucursal.getNombre();
				}
				if (atributo.equals("hapertura")) {
					return sucursal.getHapertura();
				}
				if (atributo.equals("hcierre")) {
					return sucursal.getHcierre();
				}
				if (atributo.equals("estado")) {
					return String.valueOf(sucursal.isEstado());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "s";
	}

	public DefaultTableModel generadorDeTabla() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "HORARIO APERTURA", "HORARIO CIERRE", "ESTADO" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel", SucursalModel.class).list();
			for (SucursalModel entidad : resultados) {
				if(!entidad.getNombre().equals("CENTRAL") && !entidad.getNombre().equals("PUERTO")){
					Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(), entidad.getHcierre(),
							entidad.isEstado()};
					modelo.addRow(fila);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}

	public DefaultTableModel filtrarTablaPorNombre(String nombre) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "HORARIO APERTURA", "HORARIO CIERRE", "ESTADO" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel WHERE nombre LIKE :nombre")
					.setParameter("nombre", "%" + nombre + "%")
					.list();
			for (SucursalModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(), entidad.getHcierre(),
						entidad.isEstado() };
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}

	public List<SucursalModel> obtenerTodasLasSucursales() {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<SucursalModel> sucursales = session.createQuery("FROM SucursalModel", SucursalModel.class).list();
			return sucursales;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SucursalModel obtenerSucursalPorId(int id) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(SucursalModel.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public DefaultTableModel editarFilaTablaSucursal(int id, String nombre, String hapertura, String hcierre,
			boolean estado) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "HORARIO APERTURA", "HORARIO CIERRE", "ESTADO" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel").list();
			this.updateSucursal(id, new String[] { nombre, hapertura, hcierre, String.valueOf(estado) });
			for (SucursalModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(), entidad.getHcierre(),
						entidad.isEstado() };
				modelo.addRow(fila);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}
}