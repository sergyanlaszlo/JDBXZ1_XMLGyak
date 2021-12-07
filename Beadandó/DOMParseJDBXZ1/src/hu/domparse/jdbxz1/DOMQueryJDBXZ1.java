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
			//DocumentBuilderFactory p�ld�nyos�t�sa, majd ezt megh�vva DocumentBuilder l�trehoz�sa
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//Dokumentum objektum l�trehoz�sa az XML f�jlunkb�l, majd normaliz�l�s
			Document doc = builder.parse(new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml"));
			doc.getDocumentElement().normalize();
			//Xpath l�trehoz�sa hasonl�an
			XPath xPath = XPathFactory.newInstance().newXPath();
			//Lek�rdez�s stringk�nt, 1000Ft feletti �telek
			String query = "etelek/etel[ar>1000]";
			//lista l�trehoz�sa, a lek�rdez�snek megfelel� elemekkel
			NodeList nList=(NodeList) xPath.compile(query).evaluate(doc,XPathConstants.NODESET);
			//for ciklus a nodeokon val� iter�ci�hoz.
			
			for(int i =0; i<nList.getLength();i++) {
				Node node=nList.item(i);
				//�tel ki�r�sa
				System.out.println(node.getNodeName());
				//Ha element t�pus� �s "etel nevu"
				if(node.getNodeType() ==Node.ELEMENT_NODE && node.getNodeName().equals("etel")) {
					//Akkor a nodeot elementt� konvert�ljuk
					Element elem = (Element) node;
					//Majd ennek az elementnek ki�rjuk az adatait, a rendel�ssz�m attrib�tum, �gy azt getAttribute-al
					System.out.println("Rendel�ssz�m : "+elem.getAttribute("rendelesszam"));
					System.out.println("�tel neve: " +elem.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println("Allerg�nek: "+elem.getElementsByTagName("allergenek").item(0).getTextContent());
					System.out.println("�r: "+elem.getElementsByTagName("ar").item(0).getTextContent());
					
				}
			}
			
			
		//Try blokk v�ge, "hibakezel�s",hiba eset�n t�j�koztatjuk a felhaszn�l�t a fell�p� hib�kr�l
		}catch(ParserConfigurationException | SAXException | XPathException | IOException e ) {
			e.printStackTrace();
		}
	}
}
