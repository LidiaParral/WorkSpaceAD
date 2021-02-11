package dam.xstream.act.main;

import java.io.FileInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.xstream.act.javabean.*;

public class LecturaFicheroXMLXstream {
	
	
	public static void main(String[] args) {
		
		try(FileInputStream fis = new FileInputStream("EmpleadosXStream.xml");){
			//Crear la estancia XSTREAM
			XStream xstream = new XStream();
			 
			//Permisos necesarios para que no de error a la hora de la lectura
			 xstream.addPermission(NoTypePermission.NONE);
			 xstream.addPermission(NullPermission.NULL);
			 xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
			 
			 //Indicar las clases que se van a utilizar para la lectura
			 //Sólo las clases permitidas.
			 Class[] clases = {ListaEmpleados.class, Empleado.class};
			 xstream.allowTypes(clases);
			 
			 //Indicar el paquete donde se encuentran las clases que se van a utilizar
			 xstream.allowTypesByWildcard(new String[] {"dam.dom.act.javabean.*"});
			 
			 //Relacionar las clases con las etiquetas
			 xstream.alias("Empleados", ListaEmpleados.class);
			 xstream.alias("DatosEmpleados", Empleado.class);
			 
			 //La lista se maneja de distinta, no hay que tenerlo en cuenta en la estructura XML
			 xstream.addImplicitCollection(ListaEmpleados.class, "listaEmple");
			 
			 //Invocar al método fromXML del elemento XSTREAM
			 ListaEmpleados listaObj = (ListaEmpleados) xstream.fromXML(fis);
			 
			 
			 //Recorrer cada empleado de la lista empleados.
			 for(Empleado emple : listaObj.getListaEmple()) {
				 System.out.println(emple);
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	 
	 
}
