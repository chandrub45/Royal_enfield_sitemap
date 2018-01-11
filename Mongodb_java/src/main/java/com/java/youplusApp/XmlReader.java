package com.java.youplusApp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class XmlReader {
	public static void getXmlDocument(String url)  {
		URL url1=null;
		URLConnection conn=null;
		InputStream is=null;
		MongoClient mongoclient = new MongoClient("localhost", 27017);
		try{
			url1 = new URL(url);
			conn = url1.openConnection();
			MongoDatabase database =mongoclient.getDatabase("Royal_enfield");
			MongoCollection<org.bson.Document> collection = database.getCollection("motor_cycles");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			is= new BufferedInputStream(conn.getInputStream());
			Document doc = builder.parse(is);
			NodeList nList = doc.getElementsByTagName("url");
            int count = nList.getLength();
            
			String s="";
			HashSet<org.bson.Document> set = new HashSet<org.bson.Document>();
			for (int temp = 0; temp < count; temp++) {
                Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String loc = eElement.getElementsByTagName("loc").item(0).getTextContent();
					if(loc.contains("royalenfield.com/motorcycles/")) {
						if(loc.contains("?")){
							String[] str = loc.split("\\?");
							loc = str[0];
						}
						s=loc.replaceAll("http://royalenfield.com/", "Home/").replaceAll("-", " ").replaceAll("/", "->");
						if(s.endsWith("->")){
							s=s.substring(0, s.length()-2);
							System.out.println(s);
						} else{
							System.out.println(s);
						}
					}
				}
				set.add(new org.bson.Document("url :",s));
			}	
			List<org.bson.Document> list = new ArrayList<org.bson.Document>(set);
			list.addAll(set);
			collection.insertMany(list);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			mongoclient.close();
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args){
		getXmlDocument("https://royalenfield.com/sitemap.xml");
	}
}
