package dam.dom.act.main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import dam.dom.act.javabean.Empleado;

public class GenerarFicheroEmpleadosXML {

	public static void main(String[] args) {
		//LECTURA
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			//Crear el NODO RAÍZ
			Document document = implementation.createDocument(null, "Empleados",null);
			document.setXmlVersion("1.0");
			
			//Generar la estructura de manera repetida, dentro del bucle del fichero binario.
			Element empleado;
			/*Estas variables estan declaras dentro del refactorizado de GENERARELEMENTO
			Text texto;
			Element id;
			Element nombre;
			Element depto;
			Element salario;*/
			
			//Código para la lectura del fichero binario
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleadosObj.dat"));) {
					
					Empleado emp;
					boolean continuar = true;
					while(continuar) {
						try {
							emp = (Empleado) ois.readObject();
							
							//Generar elemento que se crea directamente en el NODO RAÍZs
							empleado = document.createElement("empleado");   
							document.getDocumentElement().appendChild(empleado);
							
							generarElemento(document, empleado, String.valueOf(emp.getId()), "id");
							
							generarElemento(document, empleado, emp.getNombre(), "nombre");
							
							generarElemento(document, empleado, String.valueOf(emp.getDepartamento()), "departamento");
							
							generarElemento(document, empleado, String.valueOf(emp.getSalario()), "salario");
							
							/*
							Element id = document.createElement(etiqueta);
							Text texto = document.createTextNode(dato);
							id.appendChild(texto); //añadir al elemento un elemento hijo
							empleado.appendChild(id);
							
							Element nombre = document.createElement(etiqueta);
							Text texto1 = document.createTextNode(dato);
							nombre.appendChild(texto1); //añadir al elemento un elemento hijo
							empleado.appendChild(nombre); 
							
							Element depto = document.createElement("departamento");
							texto = document.createTextNode(String.valueOf(emp.getDepartamento()));
							depto.appendChild(texto); //añadir al elemento un elemento hijo
							empleado.appendChild(depto);
							
							Element salario = document.createElement("salario");
							texto = document.createTextNode(String.valueOf(emp.getSalario()));
							salario.appendChild(texto); //añadir al elemento un elemento hijo
							empleado.appendChild(salario);*/
							
								
							
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (EOFException e) {
							continuar = false;
							//Excepción: indica que ya no tiene más objetos que leer.
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException ei) {
					ei.printStackTrace();
				}
				

				Source source = new DOMSource(document);
				Result result = new StreamResult(new File("Empleados.xml"));
				
				
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				

				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				transformer.transform(source,  result);
				
				Result consola = new StreamResult(System.out);
				transformer.transform(source, consola);
				
		} catch (ParserConfigurationException e) {
			e.printStackTrace();	
		}  catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

	

	private static void generarElemento(Document document, Element empleado, String dato, String etiqueta) {
		
		//Generar de manera general un elemento
		Element elemento = document.createElement(etiqueta);
		Text texto = document.createTextNode(dato);
		elemento.appendChild(texto); //añadir al elemento EMPLEADO un elemento hijo ID, NOMBRE, DEPTO, SALARIO
		empleado.appendChild(elemento);
		
	}

	
}
