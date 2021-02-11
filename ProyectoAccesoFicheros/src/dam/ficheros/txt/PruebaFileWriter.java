package dam.ficheros.txt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaFileWriter {

	public static void main(String[] args) {
		
		FileWriter fw = null;
		FileReader fr = null;
		
		try {
			//true: añade texto a lo último que ya ha leído.
			fw = new FileWriter("ficherowriter.txt",true);
			//se comprueba con una lectura, con FileReader que es un canal de comunicacion conectado con FileWriter
			fr = new FileReader("ficherowriter.txt");
			
			//métodos
			fw.write("Esto es una prueba");
			fw.write(97); //codigo ascii 97 = a 
			fw.write("\n"); //salto de línea
			
			//método que utiliza un array de caracteres para escribir, cadena de caracteres conviertiendolo en un array.
        	String sLinea = "Esta es la segunda línea";
        	char[] linea = sLinea.toCharArray();
        	fw.write(linea);
        	
        	//confirmar la escritura hasta el cierre.
        	fw.flush();
        	
        	//Lectura del fichero
        	System.out.println("**** FICHERO WRITER ****");
        	
        	char[] buf = new char[20];
			
			int i = fr.read(buf);
			while (i != -1) {
					System.out.print(String.valueOf(buf)); //conversion de array de caracteres(buf) a String
					//reiniciar el buffer(char)
					buf = new char[20];
					i = fr.read(buf);
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) fw.close();
				if(fr != null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
