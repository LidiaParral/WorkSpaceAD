package dam.prueba.dboo.javabean;

public class Persona {

	private String nombre;
	private String ciudad;
	
	//CONSTRUCTOR VACÍO, para poder hacer consultas genéricas
	public Persona() {
	}

	public Persona(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", ciudad=" + ciudad + "]";
	}
	

	
	
	
	
}
