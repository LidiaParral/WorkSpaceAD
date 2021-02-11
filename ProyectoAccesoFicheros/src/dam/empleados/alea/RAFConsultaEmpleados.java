package dam.empleados.alea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RAFConsultaEmpleados {
	
	// 4 + 20 + 4 + 8 = 36 bytes.
	static final int TAM_REG = 36;
		
	public static void main(String[] args) {
		
		try(RandomAccessFile raf = new RandomAccessFile("empleados.dat", "rw");
				Scanner sc = new Scanner(System.in);) {
			
			
			System.out.println("Introduce el id del empleado a consultar:");
			int id = Integer.parseInt(sc.nextLine());
			
			//Se obtiene la posicion del puntero que se quiere solicitar.
			int pos = (id - 1) * TAM_REG;
			
			if(pos >= 0 && pos < raf.length()){
				

				raf.seek(pos);
				
				id = raf.readInt();
				
				char [] aNombre = new char[10];
				
				for (int i = 0; i < aNombre.length; i++) {
					aNombre[i] = raf.readChar();
					
				}
				
				String nombre = new String(aNombre);
				
				int depto = raf.readInt();
				double salario = raf.readDouble();
				
				
				System.out.println("ID:" + id + " - " + nombre.trim() + ", DEPTO: " + depto 
						+ "\nSALARIO: " + salario + " euros.");
				
			} else {
				System.out.println("El id indicando no se encuentra en el fichero");
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado");
		} catch(IOException e1){
			e1.printStackTrace();
		}

	}

}
