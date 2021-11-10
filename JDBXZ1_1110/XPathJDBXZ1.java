package xpathjdbxz11110;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;
public class XPathJDBXZ1 {
	
	public static void main(String[] args) {
		
		try {
			//File xmlFile = new File("class.xml");
			
			//DocumentBuilder létrehozása
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("student.xml");
			
			document.getDocumentElement().normalize();
			
			//az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();
			//Meg kell adni az elérési út kifejezést és a csomópont listát
			String expression = "class";
			
			//készítsünk egy listát majd a Path kifejezését meg kell írni és ki kell értékelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//for ciklus segítségével a nodelist csomópontjait végig iterálni
			for (int i =0 ; i< nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\n Aktuális elem: " +node.getNodeName());
				
				//meg kell vizsgálni a csomópontot a subelement tesztelése megtörtént.
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Hallgató ID: "+element.getAttribute("id"));
					
					System.out.println("Keresztnév: "+element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezetéknév: "+element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenév: "+element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: "+element.getElementsByTagName("kor").item(0).getTextContent());
					
					
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
