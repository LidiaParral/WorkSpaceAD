package dam.xstream.act.javabean;

import java.util.ArrayList;

public class ListaEmpleados {
	
	private ArrayList<Empleado> listaEmple;
	
	public ListaEmpleados() {
		listaEmple = new ArrayList<Empleado>();
	}
	
	public void addEmpleado(Empleado emp) {
		listaEmple.add(emp);
	}
	
	public ArrayList<Empleado> getListaEmple(){
		return listaEmple;
	}
}
