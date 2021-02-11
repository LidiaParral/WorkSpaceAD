package dam.ej2.javabean;

import java.io.Serializable;

/*1º Crear la clase FAMILIA con el implemento SERIALIZABLE,
 * también crear el CONSTRUCTOR con campos, los métodos GETS y SETS
 * y añadir el método TOSTRING para que aparezca por pantalla al usuario.
*/
public class Familia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idFamilia;
	private String nombreFam;
	
	
	public Familia(int idFamilia, String nombreFam) {
		this.idFamilia = idFamilia;
		this.nombreFam = nombreFam;
	}
	
	
	public int getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getNombreFam() {
		return nombreFam;
	}
	public void setNombreFam(String nombreFam) {
		this.nombreFam = nombreFam;
	}


	@Override
	public String toString() {
		return "Familia - ID:" + idFamilia + ", " + nombreFam;
	}
	
	
	
	
}
