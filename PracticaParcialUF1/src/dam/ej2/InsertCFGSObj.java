package dam.ej2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dam.ej2.javabean.Ciclo;
import dam.ej2.javabean.Familia;
/*3� Crear la clase main INSERTCFGSObj, a continuaci�n crear de manera global
 * un array de tipo CICLO que tambi�n contiene la clase FAMILIA,
 * 
 */
public class InsertCFGSObj {

	static Ciclo[] ciclo = {
			new Ciclo("Sistemas de Telecomunicaciones e Inform�ticos", "STI",
					new Familia(1, "Electricidad y Electr�nica"), "Ley LOE",
					2000),
			new Ciclo("Higiene Bucodental", "HB", new Familia(2, "Sanidad"),
					"Ley LOE", 2000),
			new Ciclo("Qu�mica Industrial", "QI", new Familia(3, "Qu�mica"),
					"Ley LOE", 2000),
			new Ciclo("Administraci�n y Finanzas", "AF", new Familia(4,
					"Administraci�n y Gesti�n"), "Ley LOE", 2000),
			new Ciclo(
					"Dise�o y Edici�n de Publicaciones Impresas y Multimedia",
					"DEPIM", new Familia(5, "Artes Gr�ficas"), "Ley LOE", 2000) };

	public static void main(String[] args) {
/*4� Crear los flujos de entrada y salida del fichero: flujo de salida(ObjectOutputStream)
 * y flujo de entrada(ObjectInputStream).
 * El fujo de salida ObjectOutputStream es el que procesa los datos y se ha de vincular a un objeto fileOut de la clase FileOutputStream .
 * El fujo de entrada ObjectInputStream es el que procesa los datos y se ha de vincular a un objeto fileIn de la clase FileInputStream.
 * 
 * 
 */
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("CFGS.dat"));
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("CFGS.dat"));) {
			
			/*5� El m�todo writeObject escribe los objetos al flujo de salida y los guarda en un archivo en disco.
			 * Recorrer el array CICLO, y luego escribe el objeto del array CICLO. 
			 */
			for (int i = 0; i < ciclo.length; i++) {
				oos.writeObject(ciclo[i]);
			}
			//Cerrar el flujo de salida(ObjectOutputStream)
			oos.flush();
			
			/*6� El m�todo writeObject escribe los objetos al flujo de salida y los guarda en un archivo en disco.
			 * Crear un objeto tipo CICLO,
			 * Bucle infinito(WHILE), y luego lee el objeto CICLO
			 */
			Ciclo objCiclo;
			
				try {
					while (true) {
					objCiclo = (Ciclo) ois.readObject();
					System.out.println(objCiclo);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch(EOFException eo){
					System.out.println("**Fin del fichero**");
				}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
