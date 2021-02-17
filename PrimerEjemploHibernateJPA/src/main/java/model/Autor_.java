package model;


import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-01-09T16:06:59.527+0100")
@StaticMetamodel(Autor.class)
public class Autor_ {
	public static volatile SingularAttribute<Autor, Long> id;
	public static volatile SingularAttribute<Autor, Short> anioNac;
	public static volatile SingularAttribute<Autor, String> nacionalidad;
	public static volatile SingularAttribute<Autor, String> nombre;
	public static volatile ListAttribute<Autor, Libro> libros;
}
