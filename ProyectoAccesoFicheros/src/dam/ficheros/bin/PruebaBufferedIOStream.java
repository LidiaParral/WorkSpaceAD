package dam.ficheros.bin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



//Clase para probar las clases BufferedInPutStream y BufferedOutPutStream
public class PruebaBufferedIOStream {

	public static void main(String[] args) {
		
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("PruebaFileReader.class"));
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\lidia\\Desktop\\ACCESO A DATOS\\WorkSpaceADT1\\ProyectoAccesoFicheros\\bin\\dam\\ficheros\\txt\\PruebaFileReader.class"));) {
			
			// se almacena el array byte en el buffer.
			byte [] buffer = new byte[1000];
			int leidos = 0;
			
			
			System.out.println("Inicio de la copia");
			
			//lectura y preguntar la condicion de que si se ha leido algun byte
			while(( leidos = bis.read(buffer)) > 0) {
				//Cada lectura lee 1000 y los escribe en el fichero destino
				bos.write(buffer, 0, leidos);
				//Este método solo se utiliza 
				buffer = new byte[1000];
			}
			
			System.out.println("Fin de la copia");
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no ha sido encontrado");
		} catch (IOException e1){
			e1.printStackTrace();
		}
		
		
	}

}
