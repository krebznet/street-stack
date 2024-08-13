package com.dunkware.utils.core.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class DunkJsonHttp {

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
	
	// json
	
	
	public static <T> T postBodyResponseWithBasicAuth(String urlEndpoint, Object bodyReq, Class<T> bodyResp, String username, String password) throws Exception {
	    URL url = new URL(urlEndpoint);
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setConnectTimeout(HTTP_REQ_TIMEOUT);
	    con.setReadTimeout(HTTP_RESP_TIMEOUT);
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Content-Type", "application/json");
	    con.setDoInput(true);
	    con.setDoOutput(true);

	    // Apply Basic Authentication
	    String auth = username + ":" + password;
	    String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes("utf-8"));
	    con.setRequestProperty("Authorization", "Basic " + encodedAuth);

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
	
    public static void performGetRequestBasicAuth(String urlEndpoint, Map<String, Object> queryParams, String username, String password) throws Exception {
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

        // Basic Authentication
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        con.setRequestProperty("Authorization", "Basic " + encodedAuth);

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
	
	

}
