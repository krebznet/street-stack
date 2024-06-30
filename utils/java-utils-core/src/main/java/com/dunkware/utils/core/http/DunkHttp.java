package com.dunkware.utils.core.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.dunkware.utils.core.json.DunkJson;

public class DunkHttp {
	

	// Default Connect Timeout = 3 Seconds
	public static int HTTP_REQ_TIMEOUT = 150000;
	// Default Read Timeout = 10 Seconds
	public static int HTTP_RESP_TIMEOUT = 150000;
	

	public static <T> T getBodyResponse(String urlEndpoint, Class<T> bodyResp) throws Exception {
		URL url = new URL(urlEndpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(HTTP_REQ_TIMEOUT);
		con.setReadTimeout(HTTP_RESP_TIMEOUT);
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
			try {
				return DunkJson.getObjectMapper().readValue(response.toString(), bodyResp);
			} catch (Exception e) {
				throw new Exception("Exception parsing json response " + e.toString());
			}

		}

	}
	

	public static <T> T postBodyResponse(String urlEndpoint, Object bodyReq, Class<T> bodyResp) throws Exception {
		URL url = new URL(urlEndpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(HTTP_REQ_TIMEOUT);
		con.setReadTimeout(HTTP_RESP_TIMEOUT);
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);
		String json = null;
		try {
			json = DunkJson.serialize(bodyReq);
		} catch (Exception e) {
			throw new Exception("Serialize Body Request Exception " + e.toString());
		}

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
				return DunkJson.getObjectMapper().readValue(response.toString(), bodyResp);
			} catch (Exception e) {
				throw new Exception("Exception parsing json response " + e.toString());
			}

		}

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
