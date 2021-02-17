package model;

import java.io.Serializable;
import javax.persistence.*;
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
	private List<Libro> libros;

	public Autor() {
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
	}

	public Libro addLibro(Libro libro) {
		getLibros().add(libro);
		libro.setAutor(this);

		return libro;
	}

	public Libro removeLibro(Libro libro) {
		getLibros().remove(libro);
		libro.setAutor(null);

		return libro;
	}

	public char[] mostrarLibros() {
		// TODO Auto-generated method stub
		return null;
	}

}