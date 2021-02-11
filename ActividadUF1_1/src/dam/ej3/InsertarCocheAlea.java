package dam.ej3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InsertarCocheAlea {

	static String[] aModelos = { "Opel Insignia", "Tesla ModelS", "BMW i8",
			"Seat Panda", "Hyundai Tucson" };

	static int[] aniosFab = { 2005, 2013, 2010, 1995, 2009 };

	static double[] aPrecio = { 20000, 120000, 60000, 15000, 30000 };

	static final int TAM_MODELO = 20; // (MODELO = 20 * 2 = 40 - 20 CHAR y CHAR
										// = 2 Bytes)
	// ESTRUCTURA: ID, MODELO, AÑO, PRECIO - 4 + 40 + 4 + 8 = 56
	static final int TAM_REG = 56;

	public static void main(String[] args) {

		try (RandomAccessFile raf = new RandomAccessFile("coches.dat", "rw");) {

			escribirCoches(raf);

			lecturaCoches(raf);

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void lecturaCoches(RandomAccessFile raf) throws IOException {

		int id = 0;
		char[] aModelo = new char[TAM_MODELO];
		String modelo;
		int anioFab;
		double precio;

		raf.seek(0);
		while (raf.getFilePointer() < raf.length())
		// Si la posicion del puntero es menor que el tamaño de bytes que tiene
		// el fichero,
		// no se ha terminado de leer
		{

			id = raf.readInt();

			for (int i = 0; i < aModelo.length; i++) {
				aModelo[i] = raf.readChar();
			}

			modelo = new String(aModelo);

			anioFab = raf.readInt();
			precio = raf.readDouble();

			System.out.println(id + " - " + modelo.trim()
					+ ", año de fabricación: " + anioFab + ", precio: "
					+ precio + " euros.");
		}

	}

	private static void escribirCoches(RandomAccessFile raf) throws IOException {
		int ultimoId = 0;
		if (raf.length() > 0) {
			raf.seek(raf.length() - TAM_REG);
			ultimoId = raf.readInt();
		}

		raf.seek(raf.length());

		StringBuffer sbModelo;
		for (int i = 0; i < aModelos.length; i++) {
			// Escribir el ID
			raf.writeInt(ultimoId + i + 1);

			// Escribir el MODELO
			sbModelo = new StringBuffer(aModelos[i]);
			sbModelo.setLength(TAM_MODELO);
			raf.writeChars(sbModelo.toString());// Convertir en STRING

			raf.writeInt(aniosFab[i]);
			raf.writeDouble(aPrecio[i]);
		}
	}

}
