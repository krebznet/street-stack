package com.dunkware.faces.demo.client.guotes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.dunkware.trade.feed.api.model.snapshot.EquitySnapshot;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuoteFetcher {

    private static final String ENDPOINT = "http://localhost:8987/feed/subscriptions";

    public List<EquitySnapshot> fetchSnapshots() throws Exception {
        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build the GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check for successful response code
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch equity snapshots, HTTP error code: " + response.statusCode());
        }

        // Parse the JSON response into a list of EquitySnapshot objects
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), new TypeReference<List<EquitySnapshot>>() {});
    }
}