package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.Autor;
import model.Libro;
import persistencia.PersistenciaJPA;

public class LibrosMain {
	static final String FIN_PROCESO = "F";
	static Scanner sc;
	static PersistenciaJPA p;

	public static void main(String[] args) {
		String opcion;
		sc = new Scanner(System.in);
		
		try {
			p = new PersistenciaJPA();
			
			do {			
				opcion = mostrarMenu();
				
				switch (opcion) {
				case "IL":
					insertarLibro();
					break;
				case "ML":
					modificarLibro();
					break;			
				case "BL":
					borrarLibro();
					break;
				case "CL":
					consultarLibros();
					break;
				case "CA":
					consultarAutores();
					break;
				case "F":
					System.out.println("Fin del proceso");
					break;
				default:
					System.out.println("Debe introducir una de las opciones indicadas");
					break;
				}
			} while (!opcion.equals(FIN_PROCESO));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
		
			

	}
	
	private static void modificarLibro() throws Exception {
		System.out.println("Introduce el id del libro a modificar");
		long id = Long.parseLong(sc.nextLine());
		
		Libro libroModif = solicitarLibroModif();
		System.out.println(p.modificarLibro(id, libroModif));
		
	}
	
	private static Libro solicitarLibroModif() throws Exception {
		
		System.out.println("Introduce el nuevo título");
		String titulo = sc.nextLine();
		
		System.out.println("Introduce el nuevo isbn");
		String isbn = sc.nextLine();
		
		System.out.println("Introduce la nueva categoría");
		String cat = sc.nextLine();
		
		System.out.println("Introduce el nuevo año de publicación");
		short anio = Short.parseShort(sc.nextLine());
		
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setIsbn(isbn);
		libro.setCategoria(cat);
		libro.setAnioPublic(anio);
		
		return libro;
	}

	private static void borrarLibro() {
		System.out.println("Introduce el id del libro a borrar");
		long id = Long.parseLong(sc.nextLine());
		
		System.out.println(p.borrarLibro(id));
		
	}
	
	
	private static void consultarAutores() throws Exception {
		ArrayList<Autor> listaAutores = p.consultarAutores();
		
		if (listaAutores.size() > 0) {
			for (Autor autor : listaAutores) {
				System.out.println(autor);
				System.out.println(autor.mostrarLibros());
			}
		} else {
			System.out.println("No hay autores almacenados");
		}
	}

	private static void consultarLibros() throws Exception {
		ArrayList<Libro> listaLibros = p.consultarLibros();
		
		if (listaLibros.size() > 0) {
			for (Libro libro : listaLibros) {
				System.out.println(libro);
			}
		} else {
			System.out.println("No hay libros almacenados");
		}
	}

	private static void insertarLibro() throws Exception {
		Libro libro = solicitarLibro();

		Autor autor = solicitarAutor();
		
		boolean existeL = p.comprobarExiteLibro(libro);
		
		if (existeL) {
			System.out.println("\nYa existe el libro indicado");
		} else {
			long idL = p.consultarIdLibro();
			
			libro.setId(idL+1);
			autor.addLibro(libro);
			//libro.setAutor(autor);
			
			System.out.println(p.insertarLibro(libro));
		}
		
		/*long idA = p.consultarIdAutor();
		autor.setId(idA+1);*/
		
		
	}

	
	private static Libro solicitarLibro() throws Exception {
		
		System.out.println("Introduce el título");
		String titulo = sc.nextLine();
		
		System.out.println("Introduce el isbn");
		String isbn = sc.nextLine();
		
		System.out.println("Introduce la categoría");
		String cat = sc.nextLine();
		
		System.out.println("Introduce el año de publicación");
		short anio = Short.parseShort(sc.nextLine());
		
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setIsbn(isbn);
		libro.setCategoria(cat);
		libro.setAnioPublic(anio);
		
		return libro;
	}

	private static Autor solicitarAutor() throws Exception {
		
		System.out.println("Introduce el nombre del autor");
		String nombre = sc.nextLine();
		
		System.out.println("Introduce la nacionalidad");
		String nacionalidad = sc.nextLine();
		
		System.out.println("Introduce el año de nacimiento");
		short anio = Short.parseShort(sc.nextLine());
		
		Autor autor = new Autor();
		autor.setNombre(nombre);
		autor.setNacionalidad(nacionalidad);
		autor.setAnioNac(anio);
		
		return autor;
	}

	private static String mostrarMenu() {
		System.out.println("\nIntroducir: ");
		System.out.println("-IL para insertar libro");
		System.out.println("-ML para modificar libro");	
		System.out.println("-BL para borrar libro");	
		System.out.println("-CL para consultar libros");
		System.out.println("-CA para consultar autores");
		System.out.println("-"+ FIN_PROCESO + " para terminar y salir");
		
		return sc.nextLine().toUpperCase();
	}

}
