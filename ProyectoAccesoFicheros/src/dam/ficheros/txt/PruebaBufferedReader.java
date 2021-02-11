package dam.ficheros.txt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PruebaBufferedReader {

	public static void main(String[] args) {
		
		try(BufferedReader bfr = new BufferedReader(new FileReader("fichero1.txt"));) {
			
			String linea;
			
			//se le añade el valor de la variable(linea), para no tener que repetir
			//Leer linea a linea del fichero seleccionado.
			while((linea = bfr.readLine()) != null){
				System.out.println(linea);
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no ha sido encontrado");
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}

}
