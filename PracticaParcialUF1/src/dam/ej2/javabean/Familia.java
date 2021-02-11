package dam.ej2.javabean;

import java.io.Serializable;

/*1� Crear la clase FAMILIA con el implemento SERIALIZABLE,
 * tambi�n crear el CONSTRUCTOR con campos, los m�todos GETS y SETS
 * y a�adir el m�todo TOSTRING para que aparezca por pantalla al usuario.
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
