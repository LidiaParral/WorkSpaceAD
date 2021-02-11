package dam.ficheros.txt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PruebaFileReader {
	
	public static void main(String[] args) {
		
		//Crear el flujo de datos de ficheros
		FileReader fr = null;
		
		try {
			fr = new FileReader("fichero1.txt");
			
			System.out.println("*** FICHERO 1 ***");
			//M�TODO READ: Lee car�cter a car�cter
			int i = fr.read();
			while (i != -1) {
					System.out.print((char)i);
					i = fr.read();
			}
			//Primero se tiene que controlar la excepci�n del fichero.
		} catch (FileNotFoundException e) { //Si se ejecuta el fichero sin estar creado, salta la excepci�n.
			System.out.println("El fichero no ha sido encontrado.");
		//El m�todo del lectura puede contener alg�n error por lo que comprueba el error de lectura que se produce en el fichero 
		} catch (IOException e) {
			System.out.println("Se ha producido un error en la lectura");
		} finally{
			try {
				if(fr != null) fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
