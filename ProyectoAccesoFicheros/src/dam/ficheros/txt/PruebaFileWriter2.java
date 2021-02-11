package dam.ficheros.txt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaFileWriter2 {

	public static void main(String[] args) {
		
		
		//Al declarar los objetos que presentan un recurso, dentro del try no es necesario encargarse del cierre.
		//No tener que gestionar el cierre de los recursos(cierre automático).
		try(FileWriter fw = new FileWriter("ficherowriter.txt",true);
				FileReader fr = new FileReader("ficherowriter.txt");) {
			
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
		} 

	}

}
