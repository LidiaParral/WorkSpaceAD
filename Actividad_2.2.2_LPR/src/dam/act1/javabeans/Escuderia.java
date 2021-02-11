package dam.act1.javabeans;

public class Escuderia {

	private int codigo;
	private String nombre;
	private String nacionalidad;



	public Escuderia(int codigo, String nombre, String nacionalidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	

	public Escuderia(String nombre, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}



	public Escuderia(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}



	public Escuderia(String nomEscuderia) {
		this.nombre = nomEscuderia;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Escudería - código:" + codigo + ", nombre: " + nombre + ", nacionalidad:" + nacionalidad;
	}
	
	
	
}
