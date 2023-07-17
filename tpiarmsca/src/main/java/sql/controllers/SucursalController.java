package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.SucursalModel;
@SuppressWarnings("serial")
public class SucursalController {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(SucursalModel.class).buildSessionFactory();

	public void createSucursal(String nombre, String hapertura, String hcierre, boolean estado) {
		try {
			Session session = sessionFactory.openSession();
			SucursalModel sucursal = new SucursalModel(nombre, hapertura, hcierre, estado);
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
			Session session = sessionFactory.openSession();
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
			Session session = sessionFactory.openSession();
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

	
	public String getAtributoSucursal(int id,String atributo) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if (atributo.equals("nombre") ) {
				return sucursal.getNombre();
			}
			if (atributo.equals("hapertura")) {
				return sucursal.getHapertura();
			}
			if (atributo == "hcierre") {
				return sucursal.getHcierre();
			}
			if (atributo == "estado") {
				return "true";
			}
			session.getTransaction().commit();
			sessionFactory.close();
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
		try {
			Session session = sessionFactory.openSession();
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
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<SucursalModel> resultados = session.createQuery("FROM SucursalModel").list();
			int tamNombre = nombre.length();
			for (SucursalModel entidad : resultados ) {
				if(entidad.getNombre().length()>=tamNombre) {
					if(entidad.getNombre().substring(0, tamNombre).equals(nombre) || nombre.isEmpty()){
						Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getHapertura(),
								entidad.getHcierre(), entidad.isEstado() };
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
	
	public List<SucursalModel> obtenerTodasLasSucursales() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<SucursalModel> sucursales = session.createQuery("FROM SucursalModel", SucursalModel.class).list();
            session.close();
            
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
	            e.printStackTrace();
	            return null;
	        }
	    }

}
