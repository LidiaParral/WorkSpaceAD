package dam.dom.act.main;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dam.dom.act.javabean.Empleado;

public class GenerarFicheroBin {

	public static void main(String[] args) {
		
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleadosObj.dat"));){
			
			Empleado emp;
			for (int i = 0; i < salarios.length; i++) {
				emp = new Empleado(i+1, nombres[i], departamentos[i], salarios[i]);
				oos.writeObject(emp);
			}

			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ei) {
			ei.printStackTrace();
		}
		
		
		
	}

}
