package dam.sax;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class LecturaFicheroSAX2 {

	public static void main(String[] args) {
		try {
			XMLReader procXML = XMLReaderFactory.createXMLReader();
			GestorContenidoSAX2 gestor = new GestorContenidoSAX2();
			procXML.setContentHandler(gestor);
			InputSource fileXML = new InputSource("AndroidManifest.xml");
			procXML.parse(fileXML);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
