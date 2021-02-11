package dam.sax;


	import org.xml.sax.Attributes;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;

	public class GestorContenidoSAX2 extends DefaultHandler{
		@Override
		public void startDocument() throws SAXException {
			System.out.println("Comienzo del documento XML");
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, 
				Attributes attributes) throws SAXException {
			System.out.println();
			System.out.println("<" + localName + ">");
			System.out.println("uri de " + localName + ":" + uri);
			System.out.println("qName: " + qName);
			System.out.println("** ATRIBUTOS DE " + localName);
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println();
				System.out.println(attributes.getLocalName(i));
				System.out.println(attributes.getURI(i));
				System.out.println(attributes.getQName(i));
				System.out.println(attributes.getValue(i));
				
			}
			
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String car = new String(ch, start, length);
			car = car.replace("[\t\n]", "").trim();
			if (car.length() > 0)
				System.out.println("\t" + car);

		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			System.out.println("</" + localName + ">");
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("Fin del documento XML");
		}
	}

