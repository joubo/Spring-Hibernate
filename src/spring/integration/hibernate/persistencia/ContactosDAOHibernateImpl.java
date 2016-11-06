/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.integration.hibernate.persistencia;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import spring.integration.hibernate.entidades.Contacto;

/**
 *
 * @author jordibordoynieto
 */
public class ContactosDAOHibernateImpl implements ContactosDAO {

    private Session sesion;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long guardaContacto(Contacto contacto) throws HibernateException {
        long id = 0;
        try {
            iniciaOperacion();
            
            id = (Long) sesion.save(contacto);
            transaction.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he); 
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    @Override
    public void actualizaContacto(Contacto contacto) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(contacto);
            transaction.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminaContacto(Contacto contacto) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(contacto);
            transaction.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    @Override
    public Contacto obtenContacto(long idContacto) throws HibernateException {
        Contacto contacto = null;
        try {
            iniciaOperacion();
            contacto = (Contacto) sesion.get(Contacto.class, idContacto);
        } finally {
            sesion.close();
        }

        return contacto;
    }

    @Override
    public List<Contacto> obtenListaContactos() throws HibernateException {
        List<Contacto> listaContactos = null;

        try {
            iniciaOperacion();
            listaContactos = sesion.createQuery("from Contacto").list();
        } finally {
            sesion.close();
        }

        return listaContactos;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = sessionFactory.openSession();
        transaction = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        transaction.rollback();
    }
}