package dam.ej1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dam.ej1.javabean.Producto;

public class InsertarProductos {

	static Producto[] arrayProductos = {
			new Producto(1, "teclado", "40 x 18 x 3", 20),
			new Producto(2, "ratón", "pequeño", 12),
			new Producto(3, "pantalla", "15,2\"", 250),
			new Producto(4, "CPU", "40 x 15 x 40", 400) };

	public static void main(String[] args) {
		// ESCRITURA
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("productos.dat"));
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("productos.dat"))) {

			for (int i = 0; i < arrayProductos.length; i++) {
				oos.writeObject(arrayProductos[i]);
			}

			oos.flush();

			Producto producto;
			try {
				while (true) {
					producto = (Producto) ois.readObject();
					System.out.println(producto); // Muestra la dirección de los
													// productos
				}
			} catch (EOFException e) {
				System.out.println("**Final del fichero**");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
