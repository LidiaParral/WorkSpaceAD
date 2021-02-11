package dam.ficheros.txt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PruebaFileReader2 {
	
	public static void main(String[] args) {
		
		//Crear el flujo de datos de ficheros
		FileReader fr = null;
		
		try {
			fr = new FileReader("fichero1.txt");
			
			System.out.println("*** FICHERO 1 ***");
			//MÉTODO READ: Lee carácter a carácter
			
			char[] buf = new char[20];
			
			int i = fr.read(buf);
			while (i != -1) {
					System.out.print(String.valueOf(buf)); //conversion de array de caracteres(buf) a String
					//reiniciar el buffer(char)
					buf = new char[20];
					i = fr.read(buf);
			}
			//Primero se tiene que controlar la excepción del fichero.
		} catch (FileNotFoundException e) { //Si se ejecuta el fichero sin estar creado, salta la excepción.
			System.out.println("El fichero no ha sido encontrado.");
		//El método del lectura puede contener algún error por lo que comprueba el error de lectura que se produce en el fichero 
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
