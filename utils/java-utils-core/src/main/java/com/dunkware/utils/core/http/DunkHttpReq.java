package com.dunkware.utils.core.http;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class DunkHttpReq {
	

	
	public static String getURLContent(String endpoint, int timeout) throws IOException { 
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(timeout));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(timeout));  
		return getURLContent(endpoint);
	}
	
	public static String getURLContent(String endpoint) throws IOException { 
		  URL url = new URL(endpoint);
	      //Retrieving the contents of the specified page
	      Scanner sc = new Scanner(url.openStream());
	      //Instantiating the StringBuffer class to hold the result
	      StringBuffer sb = new StringBuffer();
	      while(sc.hasNext()) {
	         sb.append(sc.next());
	         //System.out.println(sc.next());
	      }
	      return sb.toString();
	}

}
