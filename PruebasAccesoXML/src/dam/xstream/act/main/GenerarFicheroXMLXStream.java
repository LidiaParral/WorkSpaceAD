package dam.xstream.act.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;

import dam.xstream.act.javabean.Empleado;
import dam.xstream.act.javabean.ListaEmpleados;

public class GenerarFicheroXMLXStream {

	public static void main(String[] args) {
		
			ListaEmpleados lista = new ListaEmpleados();
			Empleado emple;
			
			//Rellenar la lista con un ArrayList con los empleados que se han leído del fichero binario.
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EmpleadosObjXStream.dat"));) {
				
				try {
					while (true) {
						emple = (Empleado) ois.readObject();
						
						lista.addEmpleado(emple);
					}
				} catch (EOFException e) {
					System.out.println("FIN DE LECTURA del fichero binario");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				//Crear una estancia XSTREAM
				XStream xstream = new XStream();
				
				//Especificar cada elemento de la estructura del fichero
				//Establecer la relacion entre las clases y las etiquetas
				xstream.alias("Empleados", ListaEmpleados.class);
				xstream.alias("DatosEmpleados", Empleado.class);
				
				//Lo más importante es que la estructura del JAVABEAN coincida con la estructura del XML
				xstream.addImplicitCollection(ListaEmpleados.class, "listaEmple");
				
				//Para generar un fichero, utilizar el metodo TOSTRING, indicar cual es la lista con el contenido
				//que se coresponde con la estructura del fichero XML
				xstream.toXML(lista, new FileOutputStream("EmpleadosXStream.xml"));
				
				
			} catch(FileNotFoundException f) {
				f.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			} 
			
			
			
			
			
	}

}
