package com.dunkware.utils.core.web;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//TODO: AVINASHANV-01 DunkWebClient 
/*
 * This was chat gbt generated its simply a component that lets you make post and get calls 
 * that is json centric with basic auth, the component does not do the json serialization 
 * or deserialization for us, we do that ourselves. this is used in some other places 
 */
public class DunkWebClient {
	
	
	
	
	public static DunkWebClient newInstance(String baseURL, String username, String password) { 
		return new DunkWebClient(baseURL, username, password);
	}
	
    private final String baseUrl;
    private final CloseableHttpClient httpClient;

    private DunkWebClient(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        this.httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
    }

    public String postJson(String path, String json, Map<String, String> queryParams) throws IOException {
        String url = buildUrl(path, queryParams);
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(json));
        HttpResponse response = httpClient.execute(post);
        checkResponse(response);
        return EntityUtils.toString(response.getEntity());
    }

    public String getJson(String path, Map<String, String> queryParams) throws IOException {
        String url = buildUrl(path, queryParams);
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        checkResponse(response);
        return EntityUtils.toString(response.getEntity());
    }

    public void getNoResponse(String path, Map<String, String> queryParams) throws IOException {
        String url = buildUrl(path, queryParams);
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        checkResponse(response);
    }

    public String get(String path, Map<String, String> queryParams) throws IOException {
        String url = buildUrl(path, queryParams);
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        checkResponse(response);
        return EntityUtils.toString(response.getEntity());
    }

    private String buildUrl(String path, Map<String, String> queryParams) {
        String query = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        return baseUrl + path + (query.isEmpty() ? "" : "?" + query);
    }

    private void checkResponse(HttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            throw new IOException("HTTP error: " + statusCode);
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
