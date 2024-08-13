package com.dunkware.utils.core.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
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
	

    public static void performGetRequest(String urlEndpoint, Map<String, Object> queryParams) throws Exception {
        // Construct query string from the map
        StringBuilder queryString = new StringBuilder();
        if (queryParams != null && !queryParams.isEmpty()) {
            queryString.append("?");
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                if (queryString.length() > 1) {
                    queryString.append("&");
                }
                queryString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                queryString.append("=");
                queryString.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
            }
        }

        // Append query string to URL
        URL url = new URL(urlEndpoint + queryString.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(HTTP_REQ_TIMEOUT);
        con.setReadTimeout(HTTP_RESP_TIMEOUT);
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        // Check HTTP response code
        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("HTTP GET request failed with response code " + responseCode);
        }

        // Read response (if needed, here for demonstration)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            // You can print the response or log it if needed
            System.out.println("Response: " + response.toString());
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
	
	  public static String getWithBasicAuth(String urlEndpoint, Map<String, String> queryParams, String username, String password) throws Exception {
	        // Build query string
	        StringBuilder queryString = new StringBuilder();
	        if (queryParams != null && !queryParams.isEmpty()) {
	            queryString.append("?");
	            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
	                queryString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
	                           .append("=")
	                           .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
	                           .append("&");
	            }
	            queryString.deleteCharAt(queryString.length() - 1); // Remove trailing '&'
	        }

	        URL url = new URL(urlEndpoint + queryString.toString());
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setConnectTimeout(10000); // 10 seconds
	        con.setReadTimeout(10000); // 10 seconds
	        con.setRequestMethod("GET");

	        // Apply Basic Authentication
	        String auth = username + ":" + password;
	        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
	        con.setRequestProperty("Authorization", "Basic " + encodedAuth);

	        int responseCode = con.getResponseCode();
	        if (responseCode >= 400) { // If response is not successful
	            throw new Exception("HTTP request failed with response code: " + responseCode);
	        }

	        // Read the response
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
	            StringBuilder response = new StringBuilder();
	            String responseLine;
	            while ((responseLine = br.readLine()) != null) {
	                response.append(responseLine.trim());
	            }
	            return response.toString();
	        }
	    }
	
	
	

}
