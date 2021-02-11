package dam.ficheros.bin;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Clase para probar las clases DataInPutStream y DateOutPutStream
public class PruebaDataIOStream {

	public static void main(String[] args) {
		
		String[] nombres = { "Juan", "Miguel", "Carlos", "Javier", "Pedro"};
		int[] edades = { 20, 19, 22, 23, 19};

		
		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("alumnos.dat"));
			DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.dat"));) {
			
			//ESCRITURA
			for (int i = 0; i < edades.length; i++) {
				dos.writeUTF(nombres[i]);
				dos.writeInt(edades[i]);
			}
			
			dos.flush();
			
			//LECTURA
			String nombre;
			int edad;
			
			//bucle infinito, para identificar el final del fichero y hay que controlarlo porque salta excepcion
			try{
				while(true){
						nombre = dis.readUTF();
						edad = dis.readInt();
						System.out.println(nombre + " - " + edad);
				}
			} catch(EOFException e){
				System.out.println("** FIN DEL FICHERO **");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no ha sido encontrado");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}

}
