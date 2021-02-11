package dam.ej1.javabean;

import java.io.Serializable;

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id; // tipo primitivo
	private String nombre; // tipo referencia
	private String medidas;
	private Float precio;// tipo primitivo

	public Producto(int id, String nombre, String medidas, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.medidas = medidas;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMedidas() {
		return medidas;
	}

	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto - " + id + ", NOMBRE: " + nombre + ", MEDIDAS: "
				+ medidas + ", PRECIO:" + precio + " euros.";
	}

}
