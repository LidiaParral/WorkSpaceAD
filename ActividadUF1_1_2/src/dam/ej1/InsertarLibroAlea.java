package dam.ej1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class InsertarLibroAlea {

	// ESTRUCUTA: ID, TITULO, AUTOR, AÑO, NUMEROPAGINAS =
	// 4 + 60 + 40 + 4 + 4
	static final int TAM_REG = 112;
	static final int TAM_TITULO = 30;
	static final int TAM_AUTOR = 20;

	public static void main(String[] args) {

		try (RandomAccessFile raf = new RandomAccessFile("libros.dat", "rw");) {

			escribirLibros(raf);

			lecturaLibros(raf);

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void lecturaLibros(RandomAccessFile raf) throws IOException {
		//LECTURA
		raf.seek(0);
		
		
		int id = 0;
		String titulo;
		char [] libTitulo = new char[TAM_TITULO];
		String autor;
		char [] libAutor = new char[TAM_AUTOR];
		int anio;
		int numPag;

		
		while (raf.getFilePointer() < raf.length())
		// Si la posicion del puntero es menor que el tamaño de bytes que tiene
		// el fichero,
		// no se ha terminado de leer
		{

			id = raf.readInt();

			for (int i = 0; i < libTitulo.length; i++) {
				libTitulo[i] = raf.readChar();
			}

			titulo = new String(libTitulo);
			
			for (int i = 0; i < libAutor.length; i++) {
				libAutor[i] = raf.readChar();
			}
			
			autor = new String(libAutor);
			anio = raf.readInt();
			numPag = raf.readInt();

			System.out.println(id + " - " + titulo.trim()
					+ ", Autor: " + autor.trim() + " Año de edición: "
					+ anio + " Número de páginas:" + numPag + " páginas.");
		}
 
	}

	private static void escribirLibros(RandomAccessFile raf) throws IOException {
		//ESCRITURA
		Scanner sc = new Scanner(System.in);

		// Leer el último id
		int id = 0;

		if (raf.length() > 0) {
			// Posicion del último registro
			raf.seek(raf.length() - TAM_REG);
			id = raf.readInt();
		}

		raf.seek(raf.length()); // Posición final para poder escribir

		boolean continuar = true;
		boolean datoOk = false;

		String titulo;
		int anio;
		int numPag;
		StringBuffer sbTitulo;
		StringBuffer sbAutor;

		while (continuar) {
			// Este paso es lo mismo que un contador
			// Es decir si tienes 0 libros, se aumenta a 1 y después utiliza el
			// id = 1
			id++;
			raf.writeInt(id);

			System.out.println("Introduce el título del libro:");
			titulo = sc.nextLine();

			// Especificar el tamaño de la cadena de caracteres
			sbTitulo = new StringBuffer(titulo);
			sbTitulo.setLength(TAM_TITULO);
			// Escribir la cadena de caracteres
			raf.writeChars(sbTitulo.toString());

			System.out.println("Introduce el autor del libro:");
			sbAutor = new StringBuffer(sc.nextLine());
			sbAutor.setLength(TAM_AUTOR);
			raf.writeChars(sbAutor.toString());

			while (!datoOk) {
				try {
					System.out.println("Introduce el año:");
					anio = Integer.parseInt(sc.nextLine());

					datoOk = true;

					raf.writeInt(anio);
				} catch (NumberFormatException e) {
					System.out.println("El dato introducido no es válido");
				}
			}

			datoOk = false;

			while (!datoOk) {
				try {
					System.out.println("Introduce el número de páginas:");
					numPag = Integer.parseInt(sc.nextLine());

					datoOk = true;

					raf.writeInt(numPag);
				} catch (NumberFormatException e) {
					System.out.println("El dato introducido no es válido");
				}
			}
			
			System.out.println("¿Desea introducir otro libro?(SI/NO)");
			continuar = sc.nextLine().equalsIgnoreCase("si");
		}

		sc.close();

	}

}
