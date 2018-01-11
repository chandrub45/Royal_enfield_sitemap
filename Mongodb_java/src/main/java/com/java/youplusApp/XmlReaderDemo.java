package com.java.youplusApp;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlReaderDemo {

	public static void main(String[] args) {
		
		try {
			File file = new File("D:\\royal enfield\\sitemap.xml");
			InputStream inputStream= new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream,"UTF-8");

			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("url");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					
					/*  String location =eElement.getElementsByTagName("loc").item(0).getTextContent();
						String change_frequency=eElement.getElementsByTagName("changefreq").item(0).getTextContent();
						String priority =eElement.getElementsByTagName("priority").item(0).getTextContent();
						*/
						
					/*  System.out.println("Location :"+ eElement.getElementsByTagName("loc").item(0).getTextContent());
						System.out.println("Change frequency :"+ eElement.getElementsByTagName("changefreq").item(0).getTextContent());
						System.out.println("Priority :"+eElement.getElementsByTagName("priority").item(0).getTextContent());*/

					System.out.println("Location : " + eElement.getAttribute("loc"));
					System.out.println("Change frequency : " + eElement.getElementsByTagName("changefreq").item(0).getTextContent());
					System.out.println("Priority : " + eElement.getElementsByTagName("priority").item(0).getTextContent());
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

