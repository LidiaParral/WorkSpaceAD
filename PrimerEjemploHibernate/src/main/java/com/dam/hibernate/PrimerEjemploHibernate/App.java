package com.dam.hibernate.PrimerEjemploHibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.dam.hibernate.PrimerEjemploHibernate.model.Persona;

/**
 * Conenctar la BBDD
 * Almacenar un número de personas(Clase Persona)
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Crea sesiones y se encarga de establecer la conexión con la BBDD
    	SessionFactory sf = null;
        Session session = null;
        
        try {
        // Inicializamos un objeto SessionFactory con la configuración
        // del fichero hibernate.cfg.xml
            sf = new Configuration().configure().buildSessionFactory();

	    // Iniciamos una sesión
	    session = sf.openSession();
	    
        // Construimos dos objetos de tipo Persona
        Persona p1 = new Persona();
        p1.setId(1);
        p1.setNombre("Lidia");
        
        Persona p2 = new Persona();
        p2.setId(2);
        p2.setNombre("Rosa");
        
        // Iniciamos una transacción dentro de la sesión
        session.beginTransaction();

        // Almacenamos los objetos
        session.save(p1);
        session.save(p2);

        // Commiteamos la transacción
        session.getTransaction().commit();


	
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			//Cerrar las conexiones en orden inverso al que se crean.
			if(session != null) session.close();
			if(sf != null) sf.close();
		}
	    		

    }
}
