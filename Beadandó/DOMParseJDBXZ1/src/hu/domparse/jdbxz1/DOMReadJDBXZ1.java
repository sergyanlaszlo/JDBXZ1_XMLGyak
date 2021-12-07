package hu.domparse.jdbxz1;
import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMReadJDBXZ1 {
public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
	File xmlfile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml"); //File objektum létrehozása
	//DocumentBuilderFactory példányosítása, majd ezt meghívva DocumentBuilder létrehozása
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dbuilder = factory.newDocumentBuilder();
	//A Documentum példányosítása amibe parseoljuk a fájlunkat.
	Document doc = dbuilder.parse(xmlfile);
	//normalizálás
	doc.getDocumentElement().normalize();
	//Gyökérelem megjelenítése
	System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
	
	
	NodeList nList = doc.getElementsByTagName("etel");
	System.out.println("---------------------------------------------------");
	//For ciklusban végigiterálunk az elementeken.
	for(int i =0; i< nList.getLength(); i++) {
		//A nodelistekbõl egyesével node-okat készítünk
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			//A nodeokat elementté konvertáljuk
			Element elem = (Element) nNode;
			String rendelesszam = elem.getAttribute("rendelesszam");
			//Étel stringgé konvertálása
			Node node1 = elem.getElementsByTagName("nev").item(0);
			String nev = node1.getTextContent();
			Node node2 = elem.getElementsByTagName("allergenek").item(0);
			String allergenek = node2.getTextContent();
			Node node3 = elem.getElementsByTagName("ar").item(0);
			String ar = node3.getTextContent();
			//Majd az étel stringként való kiírása
			System.out.println("Étel rendelésszám: "+rendelesszam);
			System.out.println("Étel neve : "+nev);
			System.out.println("Allergének: "+allergenek);
			System.out.println("Étel ára : "+ar +"\n");
			
			//Metódusok meghívása
			System.out.println("Szakács: ");
			listSzakacs(doc, rendelesszam);
			System.out.println("-----");
			System.out.println("Pincér: ");
			listPincer(doc,rendelesszam);
			System.out.println("-----");
			System.out.println("Asztal: ");
			listAsztal(doc,rendelesszam);
			System.out.println("-----------------------------"); //Olvashatóság érdekében
		}
	}
}

public static void listSzakacs(Document doc,String rendelesszam) {
	NodeList nList = doc.getElementsByTagName("szakacs"); //Most a szakács elemeket nézzük meg
	for(int i =0; i<nList.getLength();i++) {
		Node nNode = nList.item(i);
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeressük azt a szakácst, akihez az étel rendelésszáma tartozik
				//Az ételhez hasonlóan stringgé alakítjuk 
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("fizetes").item(0);
				String fizetes = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("szuletesiDatum").item(0);
				String szuletesiDatum = node3.getTextContent();
				//Majd kiírjuk
				System.out.println("Szakács neve: " +nev);
				System.out.println("Fizetése: "+fizetes);
				System.out.println("Születési dátum: "+szuletesiDatum);
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
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeressük a pincért akihez a rendelésszám tartozik
				//Stringgé alakítjuk
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String nev = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("fizetes").item(0);
				String fizetes = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("szuletesiDatum").item(0);
				String szuletesiDatum = node3.getTextContent();
				//Majd kiírjuk
				System.out.println("Pincér neve: " +nev);
				System.out.println("Fizetése: "+fizetes);
				System.out.println("Születési dátum: "+szuletesiDatum);
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
			if(elem.getAttribute("rendelesszam").toString().equals(rendelesszam)) { //Megkeressük az asztalt ahova a rendelés készül
				//Stringgé alakítjuk
				String asztalszam = elem.getAttribute("asztalszam");
				Node node1 = elem.getElementsByTagName("foglalt").item(0);
				String foglalt = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("ferohely").item(0);
				String ferohely = node2.getTextContent();
				Node node3 = elem.getElementsByTagName("emelet").item(0);
				String emelet = node3.getTextContent();
				//Kiírjuk
				System.out.println("Asztal: " +asztalszam);
				System.out.println("Foglalt: "+foglalt);
				System.out.println("Ferohely: "+ferohely);
				System.out.println("Emelet: "+emelet);
			}
		}
	}
  }

}
