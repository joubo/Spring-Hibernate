/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.integration.hibernate.persistencia;

import java.util.List;
import org.hibernate.HibernateException;
import spring.integration.hibernate.entidades.Contacto;

/**
 *
 * @author jordibordoynieto
 */
public interface ContactosDAO {

    void actualizaContacto(Contacto contacto) throws HibernateException;

    void eliminaContacto(Contacto contacto) throws HibernateException;

    long guardaContacto(Contacto contacto) throws HibernateException;

    Contacto obtenContacto(long idContacto) throws HibernateException;

    List<Contacto> obtenListaContactos() throws HibernateException;
}