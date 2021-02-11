package dam.ficheros.bin;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dam.ficheros.javabeans.Persona;

public class PruebaObjectIOStream {

	public static void main(String[] args) {
		
		Persona[] personas = {
				new Persona ("Sara",23),
				new Persona ("María",20),
				new Persona ("Elena",16),
				new Persona ("Lidia",25)
		};
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personas.dat"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"));){
			
			//ESCRITURA
			for (int i = 0; i < personas.length; i++) {
				oos.writeObject(personas[i]);
			}
			
			oos.flush();
			
			//LECTURA
			Persona p;
			try { 
			     while ((p = (Persona) ois.readObject()) != null ) {
			    	 System.out.println(p);
				     }
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (EOFException e1){
				System.out.println("FIN DEL FICHERO");
			}
			
			
		}catch (FileNotFoundException e){
			System.out.println("El fichero no se ha encontrado");
		} catch (IOException e1){
			e1.printStackTrace();
		}

	}

}
