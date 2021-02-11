package dam.ej1.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.ej1.javabeans.Autor;
import dam.ej1.javabeans.Libro;
import dam.ej1.persistencia.AccesoBibliotecaDB;

public class GestionBiblioteca {

	static final String OPCION_IL = "IL";
	static final String OPCION_ML = "ML";
	static final String OPCION_CT = "CT";
	static final String OPCION_CL = "CL";
	static final String OPCION_SALIR = "S";
	static final int LIM_PAG = 300;
	static Scanner sc;
	static AccesoBibliotecaDB abdb;
	
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		abdb = new AccesoBibliotecaDB();

		String opcion = "";
		
		while (!opcion.equals(OPCION_SALIR)) {
			
			opcion = mostrarMenu();
			
			switch (opcion) {
			case OPCION_IL:
				// introducir libro
				introducirLibro();
				break;
			case OPCION_ML:
				// modificar libro
				modificarLibro();
				break;
			case OPCION_CT:
				// consultar todos los libros
				consultarLibros();
				break;
			case OPCION_CL:
				// consultar los libros con m�s de 300 pag
				consultarLibrosPag();
				break;
			case OPCION_SALIR:
				System.out.println("FIN DEL PROGRAMA");
				break;
			default:
				System.out.println("\nLa opci�n introducida no es una de las indicadas");
				break;
			}
			
		} 
		
		abdb.cerrarBBDD();
				
		sc.close();

	}

	private static void consultarLibros() {
		ArrayList<Libro> libros = abdb.selectLibros();
		
		if (libros.size() > 0) {
			System.out.println("\nListado de libros");
			System.out.println("-------------------");
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		} else {
			System.out.println("No existe ning�n libro en la base de datos");
		}
		
	}

	private static void modificarLibro() {
		
		System.out.println("Introduce el nombre del libro que desea modificar ");
		String titulo = sc.nextLine();
		
		int numPag = solicitarNumPag("Introduce el nuevo n�mero de p�ginas"); 
		
		//Consultar si ese LIBRO existe en la BBDD, para poder ser modificado.
		int res = abdb.updateLibro(titulo, numPag);
		
		if (res == 1) {
			// INDICAR QUE SI SE HA REALIZADO LA MODIFICACI�N
			System.out.println("La modificaci�n se ha realizado correctamente");
		} else {
			// INDICAR QUE NO SE HA REALIZADO LA MODIFICACI�N
			System.out.println("La modificaci�n no se ha realizado porque " 
			+ "no existe ning�n libro con el t�tulo " + titulo);
		}
		
	}

	//Controlar que el n�mero sea ENTERO.
	private static int solicitarNumPag(String mensaje) {
		boolean datoOk = false;
		int numPag = 0;
		
		while (!datoOk) {
			try {
				System.out.println(mensaje);
				numPag = Integer.parseInt(sc.nextLine());
				
				// comprobar que es positivo
				if (numPag <= 0) {
					System.out.println("\nDebe introducir un valor positivo");
				} else {
					datoOk = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("\nDebe introducir un valor entero");
			}
		}
		
		return numPag;
	}

	private static void consultarLibrosPag() {
		//Consulta de la colecci�n de libros de m�s de 300 p�ginas.
		//Creando un arrayList, en el que se invoca al m�todo SELECTLIBROPAG
		ArrayList<Libro> librosPag = abdb.selectLibrosPag(LIM_PAG);
		
		if (librosPag.size() > 0) {
			System.out.println("\nLibros con m�s de " + LIM_PAG + " p�ginas");
			System.out.println("--------------------------------------------");
			//Recorrer cada LIBRO del arrayList LIBROSPAG
			for (Libro libro : librosPag) {
				System.out.println(libro);
			}
		} else {
			System.out.println("No existe ning�n libro con m�s de " + LIM_PAG + " p�ginas");
		}
	}

	private static void introducirLibro() {
		// TSolicitar datos del libro
		Libro libro = solicitarDatosLibro();
		
		// invocar al m�todo de la persistencia
		abdb.insertarLibro(libro);
		
		System.out.println("\nLa inserci�n se ha realizado correctamente");
	}

	private static Libro solicitarDatosLibro() {
		System.out.println("Introduce el t�tulo");
		String titulo = sc.nextLine();
		
		System.out.println("Introduce las iniciales del Autor");
		String ini = sc.nextLine();
		
		System.out.println("Introduce el nombre del autor");
		String nombre = sc.nextLine();
		
		System.out.println("Introduce la nacionalidad del autor");
		String nac = sc.nextLine();
		
		int numPag = solicitarNumPag("Indica el n�mero de p�ginas que contiene el libro");
		
		Autor autor = new Autor(ini, nombre, nac);
		Libro libro = new Libro(titulo, autor, numPag);
		
		return libro;
	}

	private static String mostrarMenu() {
		System.out.println("\nIndica cual es la operaci�n que deseas realizar:");
		System.out.println("Introduce " + OPCION_IL + " para introducir un libro");
		System.out.println("Introduce " + OPCION_ML + " para modificar el n�mero de p�ginas");
		System.out.println("Introduce " + OPCION_CT + " para consultar todos los libros");
		System.out.println("Introduce " + OPCION_CL + " para consultar los libros con m�s de "
				+ LIM_PAG + " p�ginas");
		System.out.println("Introduce " + OPCION_SALIR + " para terminar");
		
		String resp = sc.nextLine().toUpperCase();
		return resp;
	}

}
