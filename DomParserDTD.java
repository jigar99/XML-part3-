import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class DomParserDTD {

    //main function
	public static void main(String[] args) {
		String dtdFileName = "XMLDTD.dtd";
		String xmlFileName = "XMLDocument.xml";
		Document document = parseXmlDom(xmlFileName);
		validateXml(document, dtdFileName);
	}

	// paringXMLDOM
	public static Document parseXmlDom(String xmlName) {
		Document document = null;
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			//domFactory.setValidating(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			document = builder.parse(new FileInputStream(xmlName));
			System.out.println("The parseXmlDom is Successful.");
		} catch (Exception e) {
			System.out.println("The parseXmlDom is falied.");
			   System.out.println(e.toString());
		}
		return document;
	}

	//Validation XML
	public static void validateXml(Document document, String dtdName) {

		try {
			DOMSource sourceObj = new DOMSource(document);
		    StreamResult resultObj = new StreamResult(new StringWriter());
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer = tf.newTransformer();
		    transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdName);
		    transformer.transform(sourceObj, resultObj); // result is discarded
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println("The XML validation is falied.");
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			System.out.println("The XML validation is falied.");
			e.printStackTrace();
		}
		System.out.println("The XML validation is passed.");
	}

}
