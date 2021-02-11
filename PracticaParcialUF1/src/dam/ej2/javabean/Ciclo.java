package dam.ej2.javabean;

import java.io.Serializable;

/*2º Crear la clase CICLO con el implemento SERIALIZABLE,
 * también crear el CONSTRUCTOR con campos y el objeto FAMILIA que pertenece
 * a la clase externa FAMILIA, los métodos GETS y SETS
 * y añadir el método TOSTRING para que aparezca por pantalla al usuario.
*/ 
public class Ciclo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String nombre;
	private String acronimo;
	private Familia familia;
	private String leyOrganica;
	private int duracion;
	
	
	
	public Ciclo(String nombre, String acronimo, Familia familia,
			String leyOrganica, int duracion) {
		this.nombre = nombre;
		this.acronimo = acronimo;
		this.familia = familia;
		this.leyOrganica = leyOrganica;
		this.duracion = duracion;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getAcronimo() {
		return acronimo;
	}



	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}



	public Familia getFamilia() {
		return familia;
	}



	public void setFamilia(Familia familia) {
		this.familia = familia;
	}



	public String getLeyOrganica() {
		return leyOrganica;
	}



	public void setLeyOrganica(String leyOrganica) {
		this.leyOrganica = leyOrganica;
	}



	public int getDuracion() {
		return duracion;
	}



	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	@Override
	public String toString() {
		return "Ciclo - Nombre: " + nombre + ", Acrónimo: " + acronimo
				+ ", " + familia + ", Ley orgánica: " + leyOrganica
				+ ", Duración: " + duracion + " horas.";
	}
	
	
	
	
	
}
