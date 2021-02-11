package dam.act1.javabeans;

public class Piloto{

	private int codPiloto;
	private String nombre;
	private Escuderia escuderia;
	private int codEscu;
	private int anioNac;
	private int puntos;
	
	public Piloto() {
	}

	public Piloto(int codPiloto, String nombre, Escuderia escuderia, int anioNac) {
		this.codPiloto = codPiloto;
		this.nombre = nombre;
		this.escuderia = escuderia;
		this.anioNac = anioNac;
	}
	
	
	
	public Piloto(int codPiloto, String nombre, int codEscu, int anioNac, int puntos) {
		this.codPiloto = codPiloto;
		this.nombre = nombre;
		this.codEscu = codEscu;
		this.anioNac = anioNac;
		this.puntos = puntos;
	}

	public Piloto(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}
	


	public Piloto(String nombre, Escuderia escuderia) {
		this.nombre = nombre;
		this.escuderia = escuderia;
	}

	public Piloto(String nombre, Escuderia escuderia, int anioNac) {
		this.nombre = nombre;
		this.escuderia = escuderia;
		this.anioNac = anioNac;
	}
	

	
	
	public Piloto(String nombre, Escuderia escuderia, int anioNac, int puntos) {
		this.nombre = nombre;
		this.escuderia = escuderia;
		this.anioNac = anioNac;
		this.puntos = puntos;
	}
	
	// Constructor para agregar piloto
	
	public Piloto(String nombre, int anioNac, int puntos) {
		super();
		this.nombre = nombre;
		this.anioNac = anioNac;
		this.puntos = puntos;
	}

	public int getCodPiloto() {
		return codPiloto;
	}

	public void setCodPiloto(int codPiloto) {
		this.codPiloto = codPiloto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Escuderia getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(Escuderia escuderia) {
		this.escuderia = escuderia;
	}

	public int getAnioNac() {
		return anioNac;
	}

	public void setAnioNac(int anioNac) {
		this.anioNac = anioNac;
	}
	
	

	public int getCodEscu() {
		return codEscu;
	}

	public void setCodEscu(int codEscu) {
		this.codEscu = codEscu;
	}
	

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	

	public int getPuntos() {
		return puntos;
	}

	@Override
	public String toString() {
		return "Piloto - código piloto:" + codPiloto + ", nombre:" + nombre + ", Escudería:" + escuderia + ", año de nacimiento piloto:"
				+ anioNac + ", código escudería:" + codEscu + ", puntos:" + puntos;
	}

	
	
	


}

