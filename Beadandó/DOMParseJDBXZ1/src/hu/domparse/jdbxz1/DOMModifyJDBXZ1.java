package hu.domparse.jdbxz1;
import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMModifyJDBXZ1 {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
		//Itt nincs hibakezelés, a throws miatt kihagyható a try-catch
		//File objektum létrehozása az XML fájlból
		File xmlfile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml");
		//Létrehozzuk az output fileunk ami a módosítást tartalmazza.
		File modifiedXmlFile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1MODIFIED.xml");
		//Scanner billentyûzet input olvasására.
		Scanner sc = new Scanner(System.in);
		//DocumentBuilderFactory példányosítása, majd ezt meghívva DocumentBuilder létrehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		//XML fájl beolvasása majd normalizálása
		Document doc = dBuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();
		//Lista létrehozása az etel nevu elemekbõl
		NodeList nList = doc.getElementsByTagName("etel");
		//for ciklussal iteráció
		for (int i = 0; i < nList.getLength(); i++) { 
			//A lista itemeit Nodeokban tároljuk
            Node nNode = nList.item(i);
          //A nodeot elementté konvertáljuk
            Element elem = (Element) nNode;
            //A nodeból kiszedjük azt az elemet amit módosítani kívánunk, most az árat.
            Node node1 = elem.getElementsByTagName("ar").item(0);
            //Ezt stringként eltároljuk 
            String ar = node1.getTextContent();
            //Hogy megjeleníthessük
            System.out.println("Az étel jelenlegi ára:" + ar + "FT\n");
            //Bekérjük az új árat szintén stringként
            System.out.println("Az étel új ára: \n");
            String modifiedAr = sc.next();
            //És ezt állítjuk be a nodeba új árként
            node1.setTextContent(modifiedAr);
        }
		//A scannert bezájuk
		sc.close();
		//TransformerFactory objektummal egy transformer objektumot hozunk létre.
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		//Megadjuk a forrásfájlt
		DOMSource source = new DOMSource(doc);
		//Majd "átalakítjuk" egy transformer result fileá.
		StreamResult result = new StreamResult(modifiedXmlFile);
		transformer.transform(source, result);
	}

}
