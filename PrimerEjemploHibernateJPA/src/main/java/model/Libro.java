package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LIBRO database table.
 * 
 */
@Entity
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ANIO_PUBLIC")
	private short anioPublic;

	private String categoria;

	private String isbn;

	private String titulo;

	//bi-directional many-to-one association to Autor
	@ManyToOne
	@JoinColumn(name="ID_AUTOR")
	private Autor autor;

	public Libro() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public short getAnioPublic() {
		return this.anioPublic;
	}

	public void setAnioPublic(short anioPublic) {
		this.anioPublic = anioPublic;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}