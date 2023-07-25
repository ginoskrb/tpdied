package sql.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import sql.models.DetalleOrdenModel;
import sql.models.ProductoModel;
import sql.models.StockModel;
import sql.models.SucursalModel;

@SuppressWarnings("serial")
public class StockController {
	private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(SucursalModel.class).buildSessionFactory();

	public void createStock(ProductoModel producto, SucursalModel sucursal, Integer stock) {
		try (Session session = sessionFactory.openSession()) {
			StockModel productoStock = new StockModel(producto, sucursal, stock);
			session.beginTransaction();
			session.save(productoStock);
			session.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Producto agregado correctamente", "ACEPTADO",
					JOptionPane.CLOSED_OPTION);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public DefaultTableModel generadorDeTabla(int idSucursal) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "PRODUCTO", "SUCURSAL", "STOCK" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			String hql = "FROM StockModel WHERE sucursal.id = :idSucursal";
			List<StockModel> resultados = session.createQuery(hql, StockModel.class)
					.setParameter("idSucursal", idSucursal).list();
			System.out.println(resultados);
			for (StockModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getProducto(), entidad.getSucursal(), entidad.getStock() };
				modelo.addRow(fila);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}

	public void deleteStock(int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			StockModel stock = session.get(StockModel.class, id);
			if (stock != null) {
				session.delete(stock);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateStock(int id, int cantidad) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			StockModel stock = session.get(StockModel.class, id);
			if (stock != null) {
				stock.setCantidad(cantidad);
				session.update(stock);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StockModel obtenerStockPorId(int id) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(StockModel.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public DefaultTableModel filtrarTablaPorNombre(String nombre, int idSucursal) {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "PRODUCTO", "SUCURSAL", "STOCK" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			String hql = "FROM StockModel WHERE producto.nombre LIKE :nombreProducto AND sucursal.id = :idSucursal";
			List<StockModel> resultados = session.createQuery(hql, StockModel.class)
					.setParameter("nombreProducto", "%" + nombre + "%").setParameter("idSucursal", idSucursal).list();
			for (StockModel entidad : resultados) {
				Object[] fila = { entidad.getId(), entidad.getProducto(), entidad.getSucursal(), entidad.getStock() };
				modelo.addRow(fila);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelo;
	}
	
	
	public HashMap<Integer,HashMap<Integer,Integer>> obtenerSucursalesYListaStock() {
		HashMap<Integer,HashMap<Integer,Integer>> sucursalesYStock = new HashMap<>();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			String hql = "FROM StockModel";
			List<StockModel> resultados = session.createQuery(hql, StockModel.class).list();
			for(StockModel entidad : resultados) {
				HashMap<Integer,Integer> stock = new HashMap<>();
				for (StockModel entidad2 : resultados) {
					if(entidad2.getSucursal().getId()==entidad.getSucursal().getId()) {
						stock.put(entidad2.getProducto().getId(), entidad2.getStock());
					}
				}
				sucursalesYStock.put(entidad.getSucursal().getId(),stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucursalesYStock;
	}

}
