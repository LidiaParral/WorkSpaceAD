package dam.ej2.javabean;

import java.io.Serializable;

public class Producto implements Serializable{

	
	/**Para que un programa java pueda convertir un objeto en un 
	 * montón de bytes y pueda luego recuperarlo, el objeto necesita 
	 * ser Serializable. Al poder convertir el objeto a bytes, 
	 * ese objeto se puede enviar a través de red, guardarlo en un fichero,
	 * y después reconstruirlo al otra lado de la red, 
	 * leerlo del fichero
	 **/
	private static final long serialVersionUID = 1L;
	
	private int idProducto;
	private String nombre;
	private double precio;
	
	
	public Producto(int idProducto, String nombre, double precio) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "- ID:" + idProducto + ", Nombre: " + nombre
				+ " Precio: " + precio;
	}
	
	
	
	
}
