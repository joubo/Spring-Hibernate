/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.integration.hibernate;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.integration.hibernate.entidades.Contacto;
import spring.integration.hibernate.persistencia.ContactosDAO;

/**
 *
 * @author jordibordoynieto
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ContactosDAO contactosDAO = appContext.getBean(ContactosDAO.class);        
        
        Contacto contactoRecuperado = null;  
        long idAEliminar = 0;  

        //Creamos tes instancias de Contacto 
        Contacto contacto1 = new Contacto("Contacto 1", "contacto1@contacto.com", "22345678"); 
        Contacto contacto2 = new Contacto("Nuevo Contacto 2", "contacto2@contacto.com", "37654321"); 
        Contacto contacto3 = new Contacto("Contacto 3", "contacto3@contacto.com", "55612378");
        Contacto contacto1000 = new Contacto("Contacto 1000", "contacto1000@contacto.com", "45332378");  


        //Guardamos las tres instancias, guardamos el id del contacto1 para usarlo posteriormente 
        idAEliminar = contactosDAO.guardaContacto(contacto1000);
        contactosDAO.guardaContacto(contacto1); 
        contactosDAO.guardaContacto(contacto2); 
        contactosDAO.guardaContacto(contacto3);  

        //Modificamos el contacto 2 y lo actualizamos 
        contacto2.setNombre("Contacto 2"); 
        contactosDAO.actualizaContacto(contacto2);  

        //Recuperamos el contacto1 de la base de datos 
        contactoRecuperado = contactosDAO.obtenContacto(idAEliminar); 
        System.out.println("Recuperamos a " + contactoRecuperado.getNombre());  

        //Eliminamos al contactoRecuperado (que es el contacto3) 
        contactosDAO.eliminaContacto(contactoRecuperado);  

        //Obtenemos la lista de contactos que quedan en la base de datos y la mostramos 
        List<Contacto> listaContactos = contactosDAO.obtenListaContactos();  
        System.out.println("Hay " + listaContactos.size() + " contactos en la base de datos");  

        for(Contacto c : listaContactos) 
        { 
            System.out.println("-> " + c.getNombre()); 
        } 
        
        
       
    }
    
}
