package sql.controllers;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.SucursalModel;

public class SucursalController {
	
	
	public void createSucursal(int id,String nombre,String hapertura, String hcierre, boolean estado){
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SucursalModel.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			SucursalModel sucursal = new SucursalModel(id,nombre,hapertura,hcierre,estado);
			session.beginTransaction();
			session.save(sucursal);
			session.getTransaction().commit();
			sessionFactory.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ingreso de datos incorrecto","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteSucursal(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SucursalModel.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			session.delete(sucursal);
			
			session.getTransaction().commit();
			sessionFactory.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  void  updateSucursal(int id, String atributo, String valor) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SucursalModel.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			SucursalModel sucursal = session.get(SucursalModel.class, id);
			if(atributo=="nombre") {
				sucursal.setNombre(valor);
			}
			if(atributo=="hapertura") {
				sucursal.setHapertura(valor);
			}
			if(atributo=="hcierre") {
				sucursal.setHcierre(valor);
			}
			if(atributo=="estado") {
				sucursal.setEstado(Boolean.parseBoolean(valor));
			}
			session.update(sucursal);
			
			session.getTransaction().commit();
			sessionFactory.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
