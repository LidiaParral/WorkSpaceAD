package model;


import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-12-29T14:45:48.149+0100")
@StaticMetamodel(Libro.class)
public class Libro_ {
	public static volatile SingularAttribute<Libro, Long> id;
	public static volatile SingularAttribute<Libro, Short> anioPublic;
	public static volatile SingularAttribute<Libro, String> categoria;
	public static volatile SingularAttribute<Libro, String> isbn;
	public static volatile SingularAttribute<Libro, String> titulo;
	public static volatile SingularAttribute<Libro, Autor> autor;
}
