package dam.ficheros.txt;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaBufferedWriter {

	public static void main(String[] args) {
		
		String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis"};	
		
		//Definir y declarar los dos canales de flujos. Uno de entrada(BufferedWriter) y otro de salida(BufferedReader)
		try(BufferedWriter bfw = new BufferedWriter(new FileWriter("ficheroBWriter.txt"));
		BufferedReader bfr = new BufferedReader(new FileReader("ficheroBWriter.txt"));) {
			
			//ESCRITURA
			for (int i = 0; i < lineas.length; i++) {
		        bfw.write(lineas[i]+"\n");
		    }          
			
			
			bfw.flush();
			
			//LECTURA
			String linea;
			
			System.out.println("**** FICHERO DEL WRITER ****");
			//se le añade el valor de la variable(linea), para no tener que repetir
			//Leer linea a linea del fichero seleccionado.
			while((linea = bfr.readLine()) != null){
				System.out.println(linea);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
