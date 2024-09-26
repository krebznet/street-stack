package com.dunkware.utils.core.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

public class DunkHttpHelper {
	
	
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
	
	
	public static String multipartRequest(String urlTo, String post, byte[] data, String filefield) throws ParseException, IOException {
		HttpURLConnection connection = null;
		DataOutputStream outputStream = null;
		InputStream inputStream = null;
		
		String twoHyphens = "--";
		String boundary =  "*****"+Long.toString(System.currentTimeMillis())+"*****";
		String lineEnd = "\r\n";
		
		String result = "";
		
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1*1024*1024;
		
	
		
		try {
		
			
			URL url = new URL(urlTo);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
			connection.setRequestProperty("Content-Type", "multipart/form-enums; boundary="+boundary);
			
			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream.writeBytes("Content-Disposition: form-enums; name=\"" + filefield + "\"; filename=\"" +  "pussy.bytes" +"\"" + lineEnd);
			outputStream.writeBytes("Content-Type: image/jpeg" + lineEnd);
			outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
			outputStream.writeBytes(lineEnd);
			
			ByteArrayInputStream fileInputStream = new ByteArrayInputStream(data);
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];
			
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			
			
			while(bytesRead > 0) {
				outputStream.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
			
			outputStream.writeBytes(lineEnd);
			
			// Upload POST Data
			String[] posts = post.split("&");
			int max = posts.length;
			for(int i=0; i<max;i++) {
				outputStream.writeBytes(twoHyphens + boundary + lineEnd);
				String[] kv = posts[i].split("=");
				outputStream.writeBytes("Content-Disposition: form-enums; name=\"" + kv[0] + "\"" + lineEnd);
				outputStream.writeBytes("Content-Type: text/plain"+lineEnd);
				outputStream.writeBytes(lineEnd);
				outputStream.writeBytes(kv[1]);
				outputStream.writeBytes(lineEnd);
			}
			
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			
			inputStream = connection.getInputStream();
			result = convertStreamToString(inputStream);
			
			fileInputStream.close();
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			
			return result;
		} catch(Exception e) {
			
			e.printStackTrace();
			return "error";
		}
	}
	
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	

}
