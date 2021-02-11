package dam.ej1.persistencia;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import dam.ej1.javabeans.Autor;
import dam.ej1.javabeans.Libro;

public class AccesoBibliotecaDB {
	
	private ObjectContainer db;

	public AccesoBibliotecaDB() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),	"DB4O/Biblioteca.yap");
	}
	
	public void cerrarBBDD() {
		db.close();
	}

	public void insertarLibro(Libro libro) {
		//Se necesita invocar al método CONSULTARAUTOR para comprobar si dicho AUTOR existe
		Autor autorDB = consultarAutor(libro.getAutor());
		
		//Si AUTOR es distinto de NULL.
		if (autorDB != null) {
			//Actualizar el LIBRO, con un SETAUTOR que se le asignara un AUTOR de la BBDD para que contenga 
			// la referencia para que NO se duplique el AUTOR.
			libro.setAutor(autorDB);
		}
		
		db.store(libro);
		
	}

	private Autor consultarAutor(Autor autor) {
		//Crear un objeto de tipo AUTOR
		Autor autorDB = null;
		
		//Establecer una consulta de objetos de tipo AUTOR, y buscar en QUERYBYEXAMPLE por los datos del AUTOR
		ObjectSet<Autor> osAutores = db.queryByExample(autor);
		
		//Comprobar si hay al menos un objeto de tipo AUTOR
		if (osAutores.hasNext()) {
			//El siguiente se almacena en AUTORDB y se recupera con NEXT
			autorDB = osAutores.next();
		}
		//Retornar el AUTORDB
		return autorDB;
	}

	public ArrayList<Libro> selectLibrosPag(int limPag) {
		//Crear un arrayList LIBROSPAG de objetos de tipo LIBRO
		ArrayList<Libro> librosPag = new ArrayList<Libro>();
		
		//Rellenar el arrayList
		//Crear el objecto QUERY de DB4O
		Query query = db.query();
		//Especificar en una restrincción con respecto a LIBRO, del que se va a hacer la consulta.
		query.constrain(Libro.class);
		//Indicar la restricción, en este caso es del atributo NUMPAG. La restricción sea mayor de 300 páginas.
		query.descend("numPag").constrain(Integer.valueOf(limPag)).greater();
		//query.descend("numPag").constrain(new Integer(limPag)).greater();
		
		//Recorrer el ObjectSet de tipo LIBRO
		ObjectSet<Libro> osLibros = query.execute();
		
		//Volcar en el arrayList cada uno de los objetos leídos.
		//Mientras OSLIBROS tenga un siguiente LIBRO..
		while (osLibros.hasNext()) {
			//Entonces en el arrayList LIBROSPAG, hay que añadir el LIBRO correspondiente.
			librosPag.add(osLibros.next());
		}
		
		return librosPag;
	}

	public int updateLibro(String titulo, int numPag) {
		//Comprobar que exista algún libro que coincida con el TITULO que recibimos como parámetro
		ArrayList<Libro> listaLibros = selectLibrosTitulo(titulo);
		
		int res = 0;
		if (listaLibros.size() > 0) {
			res = 1;
			//Modificar el LIBRO que se encuentra en el arrayList LISTALIBROS
			for (Libro libro : listaLibros) {
				libro.setNumPag(numPag);
				db.store(libro);
			}
		} 
		
		return res;
	}

	private ArrayList<Libro> selectLibrosTitulo(String titulo) {
		ArrayList<Libro> librosTit = new ArrayList<Libro>();
		Libro libroCons = new Libro();
		libroCons.setTitulo(titulo);
		
		ObjectSet<Libro> osLibros = db.queryByExample(libroCons);
		
		while (osLibros.hasNext()) {
			librosTit.add(osLibros.next());
		}
		
		return librosTit;
	}

	public ArrayList<Libro> selectLibros() {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		ObjectSet<Libro> osLibros = db.queryByExample(Libro.class);
		while (osLibros.hasNext()) {
			libros.add(osLibros.next());
		}
		
		return libros;
	}

}
