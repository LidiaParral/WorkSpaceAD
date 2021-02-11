package dam.ej2.javabeans;

public class Piloto {

	private int numero;
	private String nombre;
	private String escuderia;
	private int puntos;

	public Piloto() {
		// TODO Auto-generated constructor stub
	}

	public Piloto(int numero, String nombre, String escuderia, int puntos) {
		this.numero = numero;
		this.nombre = nombre;
		this.escuderia = escuderia;
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return numero + ", " + nombre + ", " + escuderia + " - Puntos: " + puntos;
	}

}
