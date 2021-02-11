package dam.sax;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class LecturaFicheroSax {

	public static void main(String[] args) {

		try {

			XMLReader procXML = XMLReaderFactory.createXMLReader();

			GestorContenidoSax gestor = new GestorContenidoSax();
			procXML.setContentHandler(gestor);
			InputSource fileXML = new InputSource("Empleados.xml");

			procXML.parse(fileXML);

		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
