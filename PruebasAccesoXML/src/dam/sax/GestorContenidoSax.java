package dam.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorContenidoSax extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Inicio del DOCUMENTO XML");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		System.out.println("<" + localName + ">");
		// localName: nombre de la etiqueta
		// qName: nombre de la etiqueta con respecto al espacio de nombres
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String texto = new String(ch, start, length);
		// Remplazar en el texto cualquier caracter como, las tabulaciones y los saltos
		// de líneas. Despreciar esos caracteres.
		texto = texto.replace("[\t\n]", "");

		if (texto.length() > 0)
			System.out.println("\t" + texto);

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		System.out.println("</" + localName + ">");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("Fin del DOCUMENTO XML");
	}
}
