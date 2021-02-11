package dam.ficheros.alea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RAFModifEnteros {

	public static void main(String[] args) {
		
		
		//RW = CONSTRUCTOR DE ESCRITURA
		try(RandomAccessFile raf = new RandomAccessFile("enteros.dat", "rw");
				Scanner sc = new Scanner(System.in);){
			//Informar al usuario de cu�ntos n�meros tiene el fichero	
			long size = raf.length();
			//Cantidad de n�meros que tiene el fichero por cada entero ocupa 4 bytes
			long numEnteros = size/4;
			System.out.println("El fichero contiene " + numEnteros + " n�meros");
			
			//Leer el fichero para mostrar al usuario el contenido
			//Empezar a leer
			long pos = 0; //Posici�n del puntero
			raf.seek(pos);
			int num;
			
			while (pos < size) {
				num = raf.readInt();
				pos +=4 ;
				
				System.out.println((pos/4) + " - " + num);
			}
			
			boolean repetir = true;
			while(repetir){
				System.out.println("Indica la posici�n del n�mero a modificar");
				pos = Long.parseLong(sc.nextLine()) - 1;
				
				if(pos <= 0 || pos > numEnteros){
					System.out.println("La posici�n indicada no es correcta." + 
				"Debe ser un valor entre 1 y " + numEnteros);
				}else{
					repetir = false;
				}
			
			}
			
			
			//Se posiciona el puntero en la posici�n del n�mero que se quiere modificar
			raf.seek(pos*4);
			
			//Mostrar el n�mero que se va a modificar
			System.out.println("El valor actual es: " + raf.readInt() );
			
			//Solicitar el nuevo valor al usuario
			System.out.println("Introduce el nuevo valor");
			num = Integer.parseInt(sc.nextLine());
			
			//Se vuelve a mostrar la posicion modificada.(Retroceder a la posici�n anterior)
			raf.seek(pos*4);
			raf.writeInt(num);
			
			System.out.println("Modificaci�n realizada con �xito");
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado");
		} catch (IOException e) {			
			e.printStackTrace();
		}

	}

}
