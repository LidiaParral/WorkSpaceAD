package dam.dom;


import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class GenerarFicheroXML {

	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			//Alumnos es el nombre del elemento raíz del documento
			Document document = implementation.createDocument(null, "Alumnos",null);
			document.setXmlVersion("1.0");
			
			Element elemento = document.createElement("alumno");   
			document.getDocumentElement().appendChild(elemento);

			
			Element elemFinal = document.createElement("num_exp");
			Text text = document.createTextNode(String.valueOf("1234567"));
			elemFinal.appendChild(text);
			elemento.appendChild(elemFinal);
			
			Element elemFinalNom = document.createElement("nombre");
			text = document.createTextNode("Pepito");
			elemFinalNom.appendChild(text);
			elemento.appendChild(elemFinalNom);
			
			Element elemFinalEd = document.createElement("edad");
			text = document.createTextNode(String.valueOf("20"));
			elemFinalEd.appendChild(text);
			elemento.appendChild(elemFinalEd);
			
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new File("Alumnos.xml"));
			
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
			//Tabulación del XML
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

}
