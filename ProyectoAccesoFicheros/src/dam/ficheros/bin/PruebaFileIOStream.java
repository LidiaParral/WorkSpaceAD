package dam.ficheros.bin;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PruebaFileIOStream {

	public static void main(String[] args) {
		
		//Si se pone TRUE FileOutPutStream("fichero.dat",true), se añade 100 numeros a los que haya en el "fichero.dat"
		//en este caso al no ponerlo, se sobreescribe lo que hay dentro del archivo "fichero.dat"
		try(FileOutputStream fos = new FileOutputStream("fichero.dat");
				FileInputStream fis = new FileInputStream("fichero.dat");) {
			
			for (int i = 1; i <= 100; i++) {
				fos.write(i);
			}
			
			fos.flush();
			
			int i;
			while ((i = fis.read()) != -1) { 
			     System.out.print(i + " "); 
			 }

			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no ha sido encontrado");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
	}

}
