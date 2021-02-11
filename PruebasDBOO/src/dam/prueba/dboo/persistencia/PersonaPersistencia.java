package dam.prueba.dboo.persistencia;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import dam.prueba.dboo.javabean.Persona;

public class PersonaPersistencia {
	
	private ObjectContainer db;
	
	
	//Establacer conexión con la BASE DE DATOS con el fichero donde se va almacenar la información y también donde se encuentra este fichero
	public PersonaPersistencia() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "DB4O/Personas.yap");
	}
	
	
	//Cerrar la BASE DE DATOS
	public void cerrarDB() {
		db.close();
	}


	public int insertarPersona(Persona persona) {
		int res = 0;
		//Comprobar que la PERSONA no existe ya en la BASE DE DATOS una persona con esos mismos datos.
		ObjectSet<Persona> osPersona = db.queryByExample(persona);
		
		if(!osPersona.hasNext()) {
			//Antes de insertar la persona, comprobar si existe
			db.store(persona);
			res = 1;
		}
		
		return res;		
		
	}


	public ArrayList<Persona> consultarPersonas() {
		
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		//
		ObjectSet<Persona> osPersona = db.queryByExample(Persona.class);
		
		while (osPersona.hasNext()) {
			listaPersonas.add(osPersona.next());
		}
		
		
		
		return listaPersonas;
	}


	public int borrarPersona(Persona persona) {
		int res = 0;
		//
		ObjectSet<Persona> osPersona = db.queryByExample(persona);
		
		if(osPersona.hasNext()) {
			//Antes de insertar la persona, comprobar si existe
			db.delete(persona);
			res = 1;
		}
		
		return res;	
		
	}


	public ArrayList<Persona> consultarPersonasPorNombre(String nombre) {
		//
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		//
		
		Query query = db.query();
		query.constrain(Persona.class);
		Constraint cons = query.descend("nombre").constrain(nombre);
		
		

		
		ObjectSet<Persona> osPersona = query.execute();
		
		while (osPersona.hasNext()) {
			listaPersonas.add(osPersona.next());
		}
		
		
		
		return listaPersonas;
	}


	public int modificarCiudad(Persona persona, String ciudad) {
		int res = 0;
			
		ObjectSet<Persona> osPersona = db.queryByExample(persona);
		Persona personaModif;
		
		if (osPersona.hasNext()) {
			personaModif = osPersona.next();
			
			personaModif.setCiudad(ciudad);
			
			db.store(personaModif);
			res = 1;
		}
		
		
		
		return res;
	}
	
	
	
}
