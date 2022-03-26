import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomParserSchema {

	//main fucntion
	public static void main(String args[]) {
		String schemaFileName = "XMLSchema.xsd";
		String xmlFileName = "XMLDocument.xml";
		Schema schema = loadSchema(schemaFileName);
		Document document = null;
		try {
			document = parseXmlDom(xmlFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		validateXml(schema, document);
	}

	//loadSchema
	public static Schema loadSchema(String schemaName) {
		Schema schema = null;
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(new File(schemaName));
			System.out.println("Loading the XML Schema is successful.");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("The XML schema is falied.");
			e.printStackTrace();
		}
		return schema;
	}

   // parseXMLDOM
	public static Document parseXmlDom(String xmlName) {
		  Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(xmlName));
			System.out.println("Parsing the XML DOM is successful.");
		} catch (Exception e) {
			   System.out.println(e.toString());
		}
		return document;
	}

	// Validate function
	public static void validateXml(Schema schema, Document document) {
		try {
			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(document));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("The XML is validation is falied.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The XML is validation is falied.");
			e.printStackTrace();
		}
		   System.out.println("The XML is validation is passed.");
	}

}
