package hu.domparse.jdbxz1;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;

public class DOMQueryJDBXZ1 {

	public static void main(String[] args) {
		try {
			//DocumentBuilderFactory példányosítása, majd ezt meghívva DocumentBuilder létrehozása
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Dokumentum objektum létrehozása az XML fájlunkból, majd normalizálás
			Document doc = builder.parse(new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml"));
			doc.getDocumentElement().normalize();
			//Xpath létrehozása hasonlóan
			XPath xPath = XPathFactory.newInstance().newXPath();
			//Lekérdezés stringként, 1000Ft feletti ételek
			String query = "etelek/etel[ar>1000]";
			//lista létrehozása, a lekérdezésnek megfelelõ elemekkel
			NodeList nList=(NodeList) xPath.compile(query).evaluate(doc,XPathConstants.NODESET);
			//for ciklus a nodeokon való iterációhoz.
			
			for(int i =0; i<nList.getLength();i++) {
				Node node=nList.item(i);
				//étel kiírása
				System.out.println(node.getNodeName());
				//Ha element típusú és "etel nevu"
				if(node.getNodeType() ==Node.ELEMENT_NODE && node.getNodeName().equals("etel")) {
					//Akkor a nodeot elementté konvertáljuk
					Element elem = (Element) node;
					//Majd ennek az elementnek kiírjuk az adatait, a rendelésszám attribútum, így azt getAttribute-al
					System.out.println("Rendelésszám : "+elem.getAttribute("rendelesszam"));
					System.out.println("Étel neve: " +elem.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Allergének: "+elem.getElementsByTagName("allergenek").item(0).getTextContent());
					System.out.println("Ár: "+elem.getElementsByTagName("ar").item(0).getTextContent());
					
				}
			}
			
			
		//Try blokk vége, "hibakezelés",hiba esetén tájékoztatjuk a felhasználót a fellépõ hibákról
		}catch(ParserConfigurationException | SAXException | XPathException | IOException e ) {
			e.printStackTrace();
		}
	}
}
