package com.java.youplusApp;

import java.io.IOException;

public class Royal_enfield {

	public static void main(String[] args) throws IOException {
		
		String url="http://royalenfield.com/re-world/gallery/videos/the-himalayan--timeless-leh?gdiufgsdiv";
		int index=url.indexOf("?");
		
		if(index !=-1)
		{
			url=url.substring(0, url.indexOf("?"));
		}
		String replaceStr=("Home /"+removeDomain(3,"/",url)).replaceAll("--", " ").replaceAll("-", " ").replaceAll("/", " -> ");
		System.out.println(replaceStr);
	}
	 public static String removeDomain(int n,String c,String s){
		int pos = 0;
		
		for(int i=0;i<n;i++)
		{
			pos=s.indexOf(c);
			s=s.substring(pos+1);
		}
		if(s.endsWith("/")){
			s=s.substring(0, s.length()-1);
		}
		else{
			s=s.substring(0, s.length());
		}
		return s;
	}
}
