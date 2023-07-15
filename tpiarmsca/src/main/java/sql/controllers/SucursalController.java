package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.SucursalModel;

public class SucursalController {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(SucursalModel.class).buildSessionFactory();
	Session session = sessionFactory.openSession();

	public void createSucursal(int id, String nombre, String hapertura, String hcierre, boolean estado) {
		try {
			SucursalModel sucursal = new SucursalModel(id, nombre, hapertura, hcierre, estado);
			session.beginTransaction();
			session.save(sucursal);
			session.getTransaction().commit();
			sessionFactory.close();
			JOptionPane.showMessageDialog(null, "Sucursal creada correctamente", "Correct", JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteSucursal(int id) {
		try {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			session.delete(sucursal);

			session.getTransaction().commit();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSucursal(int id, String atributo, String valor) {
		try {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if (atributo == "nombre") {
				sucursal.setNombre(valor);
			}
			if (atributo == "hapertura") {
				sucursal.setHapertura(valor);
			}
			if (atributo == "hcierre") {
				sucursal.setHcierre(valor);
			}
			if (atributo == "estado") {
				sucursal.setEstado(Boolean.parseBoolean(valor));
			}
			session.update(sucursal);

			session.getTransaction().commit();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel generadorDeTabla() {

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "HORARIO APERTURA", "HORARIO CIERRE", "ESTADO" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel").list();
			for (SucursalModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(), entidad.getHcierre(),
						entidad.isEstado() };
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
				new String[] { "ID", "NOMBRE", "HORARIO APERTURA", "HORARIO CIERRE", "ESTADO" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel").list();
			for (SucursalModel entidad : resultados) {
				if(entidad.getNombre()==nombre) {
					Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(), entidad.getHcierre(),
							entidad.isEstado() };
					modelo.addRow(fila);
				}
			}
			return modelo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}

}
