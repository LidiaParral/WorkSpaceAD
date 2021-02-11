package dam.ficheros.alea;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RAFEnteros {

	public static void main(String[] args) {
		
		//* Copiar la grabacion
		try (RandomAccessFile raf = new RandomAccessFile("enteros.dat", "rw");
				Scanner sc = new Scanner(System.in);){
			
			boolean continuar = true;
			int num;
			
			while (continuar) {
				//*Todos los enteros ocupan 4 bytes
				//Escribir en el fichero lo que indique el usuario
				System.out.println("Introduce un entero");
				
				num = Integer.parseInt(sc.nextLine());
				
				//Posicionamos el puntero al final para añadir, sin machacar el entero
				raf.seek(raf.length()); //tamaño = length
				raf.writeInt(num);
				
				System.out.println("¿Desea continuar introduciendo enteros en el fichero? (SI/NO)");
				continuar = (sc.nextLine().equalsIgnoreCase("si"))? true : false;
				
			}
			
			
			//Mostrar por consola el contenido del fichero
			//Posicionamos el puntero al inicio
			raf.seek(0);
			System.out.println("Contenido de enteros.dat");
			try {	
				while (true) {
					num = raf.readInt();
					System.out.print(num + " ");
				}
			} catch (EOFException e){
				System.out.println("Final del fichero");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no ha sido encontrado");
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		
		

	}

}
