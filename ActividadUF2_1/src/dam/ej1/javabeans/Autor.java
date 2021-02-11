package dam.ej1.javabeans;

public class Autor {
	
	private String iniciales;
	private String nombre;
	private String nacionalidad;
	
	public Autor() {}

	public Autor(String iniciales, String nombre, String nacionalidad) {
		this.iniciales = iniciales;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return iniciales + " - " + nombre + ", nacionalidad:" + nacionalidad;
	}
	
	
	
	

}
