package dam.empleados.alea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RAFModifEmpleados {
	
	static final int TAM_REG = 36;
	static final int TAM_NOMBRE = 10;

	public static void main(String[] args) {
		
		try(RandomAccessFile raf = new RandomAccessFile("empleados.dat", "rw");
				Scanner sc = new Scanner(System.in);){
			
			
			System.out.println("Introduce el id del empleado a modificar:");
			int id = Integer.parseInt(sc.nextLine());
			
			//Se obtiene la posicion del puntero que se quiere solicitar.
			long pos = (id - 1) * TAM_REG;
			
			if(pos >= 0 && pos < raf.length()){
				raf.seek(pos);
				
				id = raf.readInt();
				
			System.out.println("Id encontrado en el fichero.");
			
				
				char [] aNombre = new char[10];
				
				for (int i = 0; i < aNombre.length; i++) {
					aNombre[i] = raf.readChar();
					
				}
				String nombre = new String(aNombre);
				
				int depto = raf.readInt();
				double salario = raf.readDouble();
				
		
				
				//Mostrar el número que se va a modificar
				System.out.println("\n***Los datos que se desean modificar***");
				System.out.println("ID:" + id + " - " + nombre.trim() + ", DEPTO: " + depto 
						+ "\nSALARIO: " + salario + " euros.");
				
				//Solicitar los nuevos valores al usuario
				System.out.println("\nIntroduce el nombre:");
				nombre = sc.nextLine();
				
				System.out.println("Introduce el departamento:");
				depto = Integer.parseInt(sc.nextLine());
				
				System.out.println("Introduce el salario:");
				salario = Double.parseDouble(sc.nextLine());
				
				//Se vuelve a mostrar la posicion modificada.(Retroceder a la posición anterior)
				//Hay que posicionarse detras de los 4 bytes del id porque no se quiere modificar,
				//por lo tanto se situa el puntero delante del nombre hasta el salario.
				raf.seek(pos + 4);
				
				//Escribir primero el nombre
				StringBuffer sbNombre = new StringBuffer(nombre);
				sbNombre.setLength(TAM_NOMBRE);
				raf.writeChars(sbNombre.toString());
				
				//Escribir el departamento y el salario
				raf.writeInt(depto);
				raf.writeDouble(salario);
				
				
				System.out.println("\n***Modificación realizada con éxito***");
				System.out.println("Los datos que se desean modificar son:");
				System.out.println("ID:" + id + " - " + nombre.trim() + ", DEPTO: " + depto 
						+ "\nSALARIO: " + salario + " euros.");
						
			
			} else {
				System.err.println("El id indicando no se encuentra en el fichero");
			} 
					
					
				
			
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
