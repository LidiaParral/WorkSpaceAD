package dam.empleados.alea;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RAFEmpleados {
	
	static final int TAM_NOMBRE = 10;
	// 4 + 20 + 4 + 8 = 36 bytes.
	static final int TAM_REG = 36;
	static String [] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"}; 
	static int [] departamentos = {10, 20, 30, 20, 10};
	static double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};

			
	public static void main(String[] args) {
		
		try(RandomAccessFile raf = new RandomAccessFile("empleados.dat", "rw");) {
			
			escribir(raf);
			
			
			leer(raf);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado");
		} catch(IOException ie){
			ie.printStackTrace();
		}

	}


	private static void leer(RandomAccessFile raf) throws IOException {
		int id;
		
		//Colocarnos en la posición 0, para poder leer el fichero.
		raf.seek(0);
		
		int depto;
		char [] aNombre = new char[TAM_NOMBRE];
		String nombre;
		double salario;
		
		System.out.println("*** EMPLEADOS ***");
		
		//GetFilePointer te indica la posición del puntero. 
		// 36 * 5 Bytes en total
		while(raf.getFilePointer() < raf.length()){
			id = raf.readInt();
			
			//Leer carácter a carácter segun el tamaño reservado para la cadena de caracteres (nombre).
			for (int i = 0; i < aNombre.length; i++) {
				aNombre[i] = raf.readChar();
			}
			
			nombre = new String(aNombre);
			
			depto = raf.readInt();
			
			salario = raf.readDouble();
			
			
			System.out.println(id + " - " + nombre.trim() + ", DEPTO: " + depto 
					+ "\nSALARIO: " + salario + " euros.");
		}
	}


	private static void escribir(RandomAccessFile raf) throws IOException {
		//Utilizar para escribir una cadena de 10 caracteres
		StringBuffer sbNombre = null;
		
		int id = 0;
		
		//Comprobamos si el fichero tiene contenido
		if(raf.length() > 0){
			//Leer el último id, para ello nos posicionamos el curso delante del último registro
			raf.seek(raf.length() - TAM_REG);
			id = raf.readInt();
			
		}
			

		//Para no machacar el archivo, nos posicionamos en la ultima posición(length) y a partir de empezar a escribir.
		raf.seek(raf.length());
			
		for (int i = 0; i < nombres.length; i++) {
			//Escribimos primero el id del empleado
			raf.writeInt(id + i + 1);
			//Escribimos el nombre utilizando el StringBuffer
			sbNombre = new StringBuffer(nombres[i]);
			sbNombre.setLength(TAM_NOMBRE);
			raf.writeChars(sbNombre.toString());
			
			raf.writeInt(departamentos[i]);
			raf.writeDouble(salarios[i]);
			
			
		}
	}

}
