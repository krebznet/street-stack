package com.dunkware.common.util.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.dunkware.common.util.json.DJson;

public class DHttpHelper {

	// Systems can override these static variables
	// as needed. 
	
	// Default Connect Timeout = 3 Seconds 
	public static int CONNECT_TIMEOUT = 50000; 
	// Default Read Timeout = 10 Seconds 
	public static int READ_TIMEOUT = 50000; 
	
	
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
	
	
	public static Object postJsonWithResponse(String endpoint, Object request, Class responseType) throws Exception { 
		URL url = new URL(endpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(CONNECT_TIMEOUT);
		con.setReadTimeout(READ_TIMEOUT);
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);
		String json = DJson.serialize(request);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = json.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			try {
				return DJson.getObjectMapper().readValue(response.toString(), responseType);
			} catch (Exception e) {
				throw new Exception("Exception parsing json response " + e.toString());
			}

			
		}
		
	}
	
	public static String postJson(String endpoint, String path, Object request) throws IOException { 
		if(endpoint.endsWith("/") ) { 
			endpoint = endpoint.substring(0,endpoint.length() - 1);
		}
		if(path.startsWith("/") == false) { 
			path = "/" + path;
		}
		endpoint = endpoint + path;
		
		return postJson(endpoint,request);
	}
	
	public static String postJson(String endpoint, Object request) throws IOException { 
		URL url = new URL(endpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(CONNECT_TIMEOUT);
		con.setReadTimeout(READ_TIMEOUT);
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);
		String json = DJson.serialize(request);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = json.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			return response.toString();
		}
	}
	
	public static String getJson(String endpoint) throws IOException { 
		URL url = new URL(endpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(CONNECT_TIMEOUT);
		con.setReadTimeout(READ_TIMEOUT);
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);
		

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			return response.toString();
		}
	}
	
	
}
