package dam.ej1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
	
	
public class InsertPCGamingAlea {
	
	//ESTRUCUTA: ID, MODELO, MARCA, PROCESADOR, VELOCIDAD ,RAM, PRECIO =
	// 4 + 100 + 20 + 60 + 8 + 4 + 8
	static final int TAM_REG = 204;
	static final int TAM_MODELO = 50;
	static final int TAM_MARCA = 10;
	static final int TAM_PROCESADOR = 30;
	
	public static void main(String[] args) {
		
		try (RandomAccessFile raf = new RandomAccessFile("PcGaming.dat", "rw");) {

			escribirPC(raf);

			lecturaPC(raf);

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private static void lecturaPC(RandomAccessFile raf) throws IOException {
		// LECTURA
		raf.seek(0);
		
		int id = 0;
		String modelo;
		char [] pcModelo = new char[TAM_MODELO];
		String marca;
		char [] pcMarca = new char[TAM_MARCA];
		String procesador;
		char[] pcProcesador = new char[TAM_PROCESADOR];
		double velocidad;
		int ram;
		double precio;
		
		while (raf.getFilePointer() < raf.length()) {
			
			id = raf.readInt();
			
			
			for (int i = 0; i < pcModelo.length; i++) {
				pcModelo[i] = raf.readChar();
			}
			
			modelo = new String(pcModelo);
			
			for (int i = 0; i < pcMarca.length; i++) {
				pcMarca[i] = raf.readChar();
			}
			
			marca = new String(pcMarca);
			
			for (int i = 0; i < pcProcesador.length; i++) {
				pcProcesador[i] = raf.readChar();
			}
			
			procesador = new String(pcProcesador);
			
			velocidad = raf.readDouble();
			ram = raf.readInt();
			precio = raf.readDouble();
			
			System.out.println(id + ", " + modelo.trim()
					+ ", " + marca.trim() + ", " + procesador.trim() + ", "
					+ velocidad + ", " + ram + ", " + precio);
			
		}
		
		
		
	}

	private static void escribirPC(RandomAccessFile raf) throws IOException {
	
		//ESCRITURA
				Scanner sc = new Scanner(System.in);

				// Leer el último id
				int id = 0;

				if (raf.length() > 0) {
					// Posicion del último registro
					raf.seek(raf.length() - TAM_REG);
					id = raf.readInt();
				}

				raf.seek(raf.length()); // Posición final para poder escribir

				boolean continuar = true;
				boolean datoOk = false;

				
				double velocidadProcesador;
				int ram;
				double precio;
				StringBuffer sbModelo;
				StringBuffer sbMarca;
				StringBuffer sbProcesador;

				while (continuar) {
					// Este paso es lo mismo que un contador
					// Es decir si tienes 0 libros, se aumenta a 1 y después utiliza el
					// id = 1
					id++;
					raf.writeInt(id);

					System.out.println("Introduce el modelo del PC:");
					sbModelo = new StringBuffer(sc.nextLine());
					sbModelo.setLength(TAM_MODELO);
					raf.writeChars(sbModelo.toString());

					System.out.println("Introduce la marca del PC:");
					sbMarca = new StringBuffer(sc.nextLine());
					sbMarca.setLength(TAM_MARCA);
					raf.writeChars(sbMarca.toString());
					
					System.out.println("Introduce el procesador del PC:");
					sbProcesador = new StringBuffer(sc.nextLine());
					sbProcesador.setLength(TAM_PROCESADOR);
					raf.writeChars(sbProcesador.toString());
					
					while (!datoOk) {
						try {
							System.out.println("Introduce la velocidad del procesador:");
							velocidadProcesador = Double.parseDouble(sc.nextLine());

							datoOk = true;

							raf.writeDouble(velocidadProcesador);
						} catch (NumberFormatException e) {
							System.out.println("El dato introducido no es válido");
						}
					}

					datoOk = false;
					

					while (!datoOk) {
						try {
							System.out.println("Introduce la RAM:");
							ram = Integer.parseInt(sc.nextLine());

							datoOk = true;

							raf.writeInt(ram);
						} catch (NumberFormatException e) {
							System.out.println("El dato introducido no es válido");
						}
					}

					datoOk = false;

					while (!datoOk) {
						try {
							System.out.println("Introduce el precio del PC:");
							precio = Double.parseDouble(sc.nextLine());

							datoOk = true;

							raf.writeDouble(precio);
						} catch (NumberFormatException e) {
							System.out.println("El dato introducido no es válido");
						}
					}
					
					System.out.println("¿Desea introducir otro PC?(SI/NO)");
					continuar = sc.nextLine().equalsIgnoreCase("si");
				}

				sc.close();

			}
	
}
