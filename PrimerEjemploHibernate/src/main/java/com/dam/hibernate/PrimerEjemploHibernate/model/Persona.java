package com.dam.hibernate.PrimerEjemploHibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//Indica que la clase es una entidad
@Entity
public class Persona {
	
	//Clave primaria que representa la clase o entidad.
	@Id
	private int id;
	
	//Columna de la tabla que pertenece a la Clase o Entidad.
	@Column
	private String nombre;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
