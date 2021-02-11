package dam.ej1.javabeans;

public class Libro {
	
	private String titulo;
	private Autor autor;
	private int numPag;
	
	public Libro() {}

	public Libro(String titulo, Autor autor, int numPag) {
		this.titulo = titulo;
		this.autor = autor;
		this.numPag = numPag;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}

	@Override
	public String toString() {
		return titulo + "\n Autor:" + autor + "\n Número de páginas: " + numPag;
	}
	
	

}
