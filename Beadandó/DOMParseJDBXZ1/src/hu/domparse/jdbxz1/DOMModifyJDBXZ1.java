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
		//Itt nincs hibakezel�s, a throws miatt kihagyhat� a try-catch
		//File objektum l�trehoz�sa az XML f�jlb�l
		File xmlfile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1.xml");
		//L�trehozzuk az output fileunk ami a m�dos�t�st tartalmazza.
		File modifiedXmlFile = new File("src/hu/domparse/jdbxz1/XMLJDBXZ1MODIFIED.xml");
		//Scanner billenty�zet input olvas�s�ra.
		Scanner sc = new Scanner(System.in);
		//DocumentBuilderFactory p�ld�nyos�t�sa, majd ezt megh�vva DocumentBuilder l�trehoz�sa
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		//XML f�jl beolvas�sa majd normaliz�l�sa
		Document doc = dBuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();
		//Lista l�trehoz�sa az etel nevu elemekb�l
		NodeList nList = doc.getElementsByTagName("etel");
		//for ciklussal iter�ci�
		for (int i = 0; i < nList.getLength(); i++) { 
			//A lista itemeit Nodeokban t�roljuk
            Node nNode = nList.item(i);
          //A nodeot elementt� konvert�ljuk
            Element elem = (Element) nNode;
            //A nodeb�l kiszedj�k azt az elemet amit m�dos�tani k�v�nunk, most az �rat.
            Node node1 = elem.getElementsByTagName("ar").item(0);
            //Ezt stringk�nt elt�roljuk 
            String ar = node1.getTextContent();
            //Hogy megjelen�thess�k
            System.out.println("Az �tel jelenlegi �ra:" + ar + "FT\n");
            //Bek�rj�k az �j �rat szint�n stringk�nt
            System.out.println("Az �tel �j �ra: \n");
            String modifiedAr = sc.next();
            //�s ezt �ll�tjuk be a nodeba �j �rk�nt
            node1.setTextContent(modifiedAr);
        }
		//A scannert bez�juk
		sc.close();
		//TransformerFactory objektummal egy transformer objektumot hozunk l�tre.
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		//Megadjuk a forr�sf�jlt
		DOMSource source = new DOMSource(doc);
		//Majd "�talak�tjuk" egy transformer result file�.
		StreamResult result = new StreamResult(modifiedXmlFile);
		transformer.transform(source, result);
	}

}
