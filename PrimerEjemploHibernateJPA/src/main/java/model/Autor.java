package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the AUTOR database table.
 * 
 */
@Entity
@NamedQuery(name="Autor.findAll", query="SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ANIO_NAC")
	private short anioNac;

	private String nacionalidad;

	private String nombre;

	//bi-directional many-to-one association to Libro
	@OneToMany(mappedBy="autor")
	//@OrderColumn(name = "id", nullable = false)
	private List<Libro> libros;

	public Autor() {
		libros = new ArrayList<Libro>();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public short getAnioNac() {
		return this.anioNac;
	}

	public void setAnioNac(short anioNac) {
		this.anioNac = anioNac;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
		/*for (Libro libro : libros) {
			libro.setAutor(this);
		}*/
	}

	public void addLibro(Libro libro) {
		if (!libros.contains(libro)) {
			libros.add(libro);
			libro.setAutor(this);
		}

	}

	public void removeLibro(Libro libro) {
		if (libros.contains(libro)) {
			libros.remove(libro);
			libro.setAutor(null);
		}

	}

	@Override
	public String toString() {
		return id + " - " + nombre + ", Año nacimiento: " + anioNac 
				+ ", Nacionalidad: " + nacionalidad; 
				//+ mostrarLibros();
	}

	public String mostrarLibros() {
		String libros = "";
		if (this.libros != null && this.libros.size() > 0) {
			libros += "\n libros : "; 
			for (Libro libro : this.libros) {
				if (libro != null)
					libros = libros + "\n- " + libro.getTitulo();
			}
			libros += "\n";
		}
		return libros;
	}
	
	

}