package sql.controllers;




import javax.swing.JOptionPane;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sql.models.SucursalModel;
import sql.models.CaminoModel;

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
            JOptionPane.showMessageDialog(null, "Camino creado correctamente", "Correct", JOptionPane.CLOSED_OPTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso de datos incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
