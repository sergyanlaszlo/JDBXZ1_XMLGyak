package hu.domparse.jdbxz1;
import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMReadJDBXZ1 {
public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
	File xmlfile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml"); //File objektum l�trehoz�sa
	//DocumentBuilderFactory p�ld�nyos�t�sa, majd ezt megh�vva DocumentBuilder l�trehoz�sa
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dbuilder = factory.newDocumentBuilder();
	//A Documentum p�ld�nyos�t�sa amibe parseoljuk a f�jlunkat.
	Document doc = dbuilder.parse(xmlfile);
	//normaliz�l�s
	doc.getDocumentElement().normalize();
	//Gy�k�relem megjelen�t�se
	System.out.println("Gy�k�relem: " + doc.getDocumentElement().getNodeName());
	
	
	NodeList nList = doc.getElementsByTagName("etel");
	System.out.println("---------------------------------------------------");
	//For ciklusban v�gigiter�lunk az elementeken.
	for(int i =0; i< nList.getLength(); i++) {
		//A nodelistekb�l egyes�vel node-okat k�sz�t�nk
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			//A nodeokat elementt� konvert�ljuk
			Element elem = (Element) nNode;
			String rendelesszam = elem.getAttribute("rendelesszam");
			//�tel stringg� konvert�l�sa
			Node node1 = elem.getElementsByTagName("nev").item(0);
			String nev = node1.getTextContent();
			Node node2 = elem.getElementsByTagName("allergenek").item(0);
			String allergenek = node2.getTextContent();
			Node node3 = elem.getElementsByTagName("ar").item(0);
			String ar = node3.getTextContent();
			//Majd az �tel stringk�nt val� ki�r�sa
			System.out.println("�tel rendel�ssz�m: "+rendelesszam);
			System.out.println("�tel neve : "+nev);
			System.out.println("Allerg�nek: "+allergenek);
			System.out.println("�tel �ra : "+ar +"\n");
			
			//Met�dusok megh�v�sa
			System.out.println("Szak�cs: ");
			listSzakacs(doc, rendelesszam);
			System.out.println("-----");
			System.out.println("Pinc�r: ");
			listPincer(doc,rendelesszam);
			System.out.println("-----");
			System.out.println("Asztal: ");
			listAsztal(doc,rendelesszam);
			System.out.println("-----------------------------"); //Olvashat�s�g �rdek�ben
		}
	}
}

public static void listSzakacs(Document doc,String rendelesszam) {
	NodeList nList = doc.getElementsByTagName("szakacs"); //Most a szak�cs elemeket n�zz�k meg
	for(int i =0; i<nList.getLength();i++) {
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeress�k azt a szak�cst, akihez az �tel rendel�ssz�ma tartozik
				//Az �telhez hasonl�an stringg� alak�tjuk 
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("fizetes").item(0);
				String fizetes = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("szuletesiDatum").item(0);
				String szuletesiDatum = node3.getTextContent();
				//Majd ki�rjuk
				System.out.println("Szak�cs neve: " +nev);
				System.out.println("Fizet�se: "+fizetes);
				System.out.println("Sz�let�si d�tum: "+szuletesiDatum);
			}
		}
	}
}

public static void listPincer(Document doc,String rendelesszam) {
	NodeList nList = doc.getElementsByTagName("pincer");
	for(int i =0; i<nList.getLength();i++) {
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeress�k a pinc�rt akihez a rendel�ssz�m tartozik
				//Stringg� alak�tjuk
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("fizetes").item(0);
				String fizetes = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("szuletesiDatum").item(0);
				String szuletesiDatum = node3.getTextContent();
				//Majd ki�rjuk
				System.out.println("Pinc�r neve: " +nev);
				System.out.println("Fizet�se: "+fizetes);
				System.out.println("Sz�let�si d�tum: "+szuletesiDatum);
			}
		}
	}
  }

public static void listAsztal(Document doc,String rendelesszam) {
	NodeList nList = doc.getElementsByTagName("asztal");
	for(int i =0; i<nList.getLength();i++) {
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeress�k az asztalt ahova a rendel�s k�sz�l
				//Stringg� alak�tjuk
				String asztalszam = elem.getAttribute("asztalszam");
				Node node1 = elem.getElementsByTagName("foglalt").item(0);
				String foglalt = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("ferohely").item(0);
				String ferohely = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("emelet").item(0);
				String emelet = node3.getTextContent();
				//Ki�rjuk
				System.out.println("Asztal: " +asztalszam);
				System.out.println("Foglalt: "+foglalt);
				System.out.println("Ferohely: "+ferohely);
				System.out.println("Emelet: "+emelet);
			}
		}
	}
  }

}
