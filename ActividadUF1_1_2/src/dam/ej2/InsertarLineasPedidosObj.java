package dam.ej2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dam.ej2.javabean.LineaPedido;
import dam.ej2.javabean.Producto;

public class InsertarLineasPedidosObj {
	
	static LineaPedido [] arrayLineas = {
			new LineaPedido(1234,new Producto(123,"Jamón", 80),1,80,"28/10/2020"),
			new LineaPedido(1235,new Producto(125,"Queso", 25),2,50,"29/10/2020"),
			new LineaPedido(1236,new Producto(128,"Lomo", 125),4,500,"28/11/2020"),
			new LineaPedido(1237,new Producto(126,"Salchicón", 10),4,40,"03/11/2020"),
			new LineaPedido(1238,new Producto(130,"Jamón York", 8),2,16,"25/11/2020")
	};

	public static void main(String[] args) {

		// ESCRITURA
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("lineasPedidos.dat"));
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("lineasPedidos.dat"));) {

			for (int i = 0; i < arrayLineas.length; i++) {
				oos.writeObject(arrayLineas[i]);
			}

			oos.flush();
			
			LineaPedido objLP;
			try {
				while (true) {
					objLP = (LineaPedido) ois.readObject();
					System.out.println(objLP); // Muestra la dirección de los
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
