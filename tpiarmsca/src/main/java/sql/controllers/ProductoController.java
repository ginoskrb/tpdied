package sql.controllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.ProductoModel;
import sql.models.SucursalModel;

@SuppressWarnings("serial")
public class ProductoController {
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(ProductoModel.class).buildSessionFactory();

	public void createProducto(String nombre, String descripcion, float precioUnitario, float pesoKg) {
		try {
			Session session = sessionFactory.openSession();
			ProductoModel producto = new ProductoModel(nombre, descripcion, precioUnitario, pesoKg);
			session.beginTransaction();
			session.save(producto);
			session.getTransaction().commit();
			sessionFactory.close();
			JOptionPane.showMessageDialog(null, "Producto agregado correctamente", "ACEPTADO",
					JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteProducto(int id) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ProductoModel producto = session.get(ProductoModel.class, id);
			session.delete(producto);
			session.getTransaction().commit();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProducto(int id, Object[] valores) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ProductoModel producto = session.get(ProductoModel.class, id);
			producto.setNombre(String.valueOf(valores[0]));
			producto.setDescripcion(String.valueOf(valores[1]));
			producto.setPrecioUnitario((float) (valores[2]));
			producto.setPesoKg((float) (valores[3]));
			session.update(producto);

			session.getTransaction().commit();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel generadorDeTabla() {

		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "DESCRIPCION", "PRECIO UNITARIO", "PESO EN KG" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<ProductoModel> resultados = session.createQuery("FROM ProductoModel").list();
			for (ProductoModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getDescripcion(),
						entidad.getPrecioUnitario(), entidad.getPesoKg() };
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
				new String[] {  "ID", "NOMBRE", "DESCRIPCION", "PRECIO UNITARIO", "PESO EN KG" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<ProductoModel> resultados = session.createQuery("FROM ProductoModel").list();
			int tamNombre = nombre.length();
			for (ProductoModel entidad : resultados) {
				if (entidad.getNombre().length() >= tamNombre) {
					if (entidad.getNombre().substring(0, tamNombre).equalsIgnoreCase(nombre) || nombre.isEmpty()) {
						Object[] fila = { entidad.getId(), entidad.getNombre(), entidad.getDescripcion(),
								entidad.getPrecioUnitario(), entidad.getPesoKg() };
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
	
	public String getAtributoProducto(int id,String atributo) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ProductoModel producto = session.get(ProductoModel.class, id);
			if (atributo.equals("nombre") ) {
				return producto.getNombre();
			}
			if (atributo.equals("descripcion")) {
				return producto.getDescripcion();
			}
			if (atributo == "precio_unitario") {
				return String.valueOf(producto.getPrecioUnitario());
			}
			if (atributo == "peso_kg") {
				return String.valueOf(producto.getPesoKg());
			}
			session.getTransaction().commit();
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "s";
	}
}
