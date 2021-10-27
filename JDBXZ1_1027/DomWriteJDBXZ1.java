package domjdbxz11026;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomWriteJDBXZ1 {
		
	public static void main(String[] args) throws ParserConfigurationException,TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//A dokumentumkészítő gyárból egy new document builder jön létre
		
		Document doc = builder.newDocument();
		//a dokumentum készítőből új dokumentumot hozunk ltre
		
		Element root = doc.createElementNS("domJDBXZ1", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Pal", "Kiss", "Programmer"));
		root.appendChild(createUser(doc, "2", "Piroska", "Zold", "Writer"));
		root.appendChild(createUser(doc, "3", "Alma", "Gordon", "Teacher"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		
		DOMSource source = new DOMSource(doc);
		
File myFile = new File("users1.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		//írás konzolba és fájlba
		
		transf.transform(source, console);
		transf.transform(source, file);
		
		
		private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
			
			Element user = doc.createElement("user");
			
			user.setAttribute("id", id);
			user.appendChild(CreateUserElement(doc,"firstname", firstName));
			user.appendChild(createUserElement(doc,"lastname",lastName));
			user.appendChild(createUserElement(doc, "profession", profession));
			
			return user;
		}
		
		private static Node createUserElement(Document doc,String name, String value) {
			
			Element node = doc.createElement(name);
			node.appendChild(doc.createTextNode(value));
			
			return node;
		}
		
		
		
		

		
		
	}
}
