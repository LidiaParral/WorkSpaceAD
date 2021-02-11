package dam.dom.act.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class LecturaFicheroEmpleadosXML {

	public static void main(String[] args) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("Empleados.xml"));

			Node nodoRaiz = document.getFirstChild();
			String nomNR = nodoRaiz.getNodeName();
			System.out.println("Nodo Raíz: " + nomNR);

			// Los NODOSHIJOS del NODORAIZ(Asignaturas) son elementos de tipo de asignatura:
			// asignatura
			NodeList nodosNR = nodoRaiz.getChildNodes();

			accesoNodos(nodosNR, "\t");
			/*
			 * //Listado de NODOSHIJOS de los elementos del NODO(asignatura): codigo, nombre
			 * y horas NodeList nodosNA;
			 * 
			 * Node nodoAsignatura; Node nodoCNH; for (int i = 0; i < nodosNR.getLength();
			 * i++) { //Acceder al primer nodo(asignatura) nodoAsignatura = nodosNR.item(i);
			 * 
			 * //Preguntar si el NODO es de tipo ELEMENTO, tiene contenido y nombre de la
			 * etiqueta if (nodoAsignatura.getNodeType() == Node.ELEMENT_NODE) {
			 * System.out.println("Nodo del nodo raíz: " + nodoAsignatura.getNodeName());
			 * nodosNA = nodoAsignatura.getChildNodes();
			 * 
			 * for (int j = 0; j < nodosNA.getLength(); j++) { nodoCNH = nodosNA.item(j);
			 * 
			 * if (nodoCNH.getNodeType() == Node.ELEMENT_NODE) {
			 * System.out.println("Nodo del nodo del nodo raíz: " + nodoCNH.getNodeName());
			 * } else if(nodoCNH.getNodeType() == Node.TEXT_NODE) {
			 * System.out.println("El texto del nodo: " + nodoAsignatura.getNodeValue()); }
			 * } //y sino preguntar si es un NODO de tipo TEXTO. No tiene contenido por lo
			 * tanto, se accede por el valor del texto- } else
			 * if(nodoAsignatura.getNodeType() == Node.TEXT_NODE) {
			 * System.out.println("El texto del nodo: " + nodoAsignatura.getNodeValue()); }
			 * 
			 * 
			 * }
			 */

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void accesoNodos(NodeList nodosHijos, String tab) {
		Node nodoHijo;
		NodeList listaNodos;

		for (int i = 0; i < nodosHijos.getLength(); i++) {
			nodoHijo = nodosHijos.item(i);

			if (nodoHijo.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(tab + nodoHijo.getNodeName());

				listaNodos = nodoHijo.getChildNodes();
				accesoNodos(listaNodos, tab + tab);
			} else if (nodosHijos.item(0).getNodeType() == Node.TEXT_NODE) {
				System.out.println(tab + tab + nodosHijos.item(0).getNodeValue());
			}
		}
	}

}
