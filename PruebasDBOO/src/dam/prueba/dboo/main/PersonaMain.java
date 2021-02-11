package dam.prueba.dboo.main;

import java.util.ArrayList;

import dam.prueba.dboo.javabean.Persona;
import dam.prueba.dboo.persistencia.PersonaPersistencia;

public class PersonaMain {
	
	static PersonaPersistencia per;

	public static void main(String[] args) {
		

		
		Persona persona1 = new Persona("Pedro", "Sevilla");
		Persona persona2 = new Persona("Lidia", "Madrid");
		Persona persona3 = new Persona("Pedro", "Barcelona");
		Persona persona4 = new Persona("Lidia", "Madrid");
		
		//Establecer la conexión con la BASE DE DATOS
		 per = new PersonaPersistencia();
		
		//Añadir cada persona a la BASE DE DATOS
		insertar(persona1);
		insertar(persona2);
		insertar(persona3);
		insertar(persona4);
		
		mostrarPersonas();
		
		
		eliminar(persona3);
		//Eliminar una persona que no existe
		eliminar(new Persona("Víctor","Galicia"));
		
		insertar(persona3);
		
		
		mostrarPersonaPorNombre();
		
		
		modificarCiudad(persona3);
		
		mostrarPersonas();
		
		per.cerrarDB();
	}

	private static void modificarCiudad(Persona persona) {
		//
		String ciudad = "Toledo";
		
		int res = per.modificarCiudad(persona,ciudad);
		
		if(res == 1) {
			System.out.println("La modificación se ha realizado correctamente.");
		} else {
			System.out.println("No se ha podido realizar el cambio");
		}
		
	}

	private static void mostrarPersonaPorNombre() {
		//
		ArrayList<Persona> lista = per.consultarPersonasPorNombre("Pedro");
			
			if(lista.size() > 0) {
				System.out.println("Lista de personas de Nombre(Pedro):");
				
				for (Persona persona : lista) {
					System.out.println(persona);
				}
			} else {
				System.out.println("No se han encontrado personas con el nombre Pedro.");
			}
		
	}

	private static void eliminar(Persona persona) {
		int res = per.borrarPersona(persona);
		
		if(res == 1) {
			System.out.println("La eliminación se ha realizado correctamente");
		} else {
			System.out.println("No se ha eliminado porque la información " 
					+ "ya que no existe en la BBDD");
		}
		
	}

	private static void mostrarPersonas() {
		ArrayList<Persona> lista = per.consultarPersonas();
		
		if(lista.size() > 0) {
			System.out.println("Lista de personas:");
			
			for (Persona persona : lista) {
				System.out.println(persona);
			}
		} else {
			System.out.println("No se han encontrado personas.");
		}
		
	}

	private static void insertar(Persona persona) {
		int res = per.insertarPersona(persona);
		if(res == 1) {
			System.out.println("La inserción se ha realizado correctamente");
		} else {
			System.out.println("No se ha insertado porque la información " 
					+ "ya existe en la BBDD");
		}
	}

}
