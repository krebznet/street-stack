package com.dunkware.utils.reactive.client;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;

public class ReactiveClient {

    private final WebClient webClient;

    // Constructor for basic authentication
    public static ReactiveClient newInstance(String endpoint, String username, String password) {
        return new ReactiveClient(endpoint, username, password);
    }

    // Constructor for no authentication
    public static ReactiveClient newInstance(String endpoint) {
        return new ReactiveClient(endpoint);
    }

    private String endPoint;
    private String username;
    private String password;

    // Private constructor with basic authentication
    private ReactiveClient(String endpoint, String username, String password) {
        this.webClient = WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeaders(headers -> headers.setBasicAuth(username, password))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        this.endPoint = endpoint;
        this.username = username;
        this.password = password;
    }
    
    public Flux<byte[]> getBinaryFluxResponse(String path, Map<String, Object> queryParams) {
        return webClient.get()
            .uri(buildUrlWithParams(path, queryParams))
            .retrieve()
            .bodyToFlux(byte[].class)
            .doOnError(WebClientResponseException.class, this::handleHttpError);
    }

    // Private constructor for no authentication
    private ReactiveClient(String endpoint) {
        this.webClient = WebClient.builder()
                .baseUrl(endpoint)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        this.endPoint = endpoint;
    }

    // Helper method to build URL with query parameters
    private String buildUrlWithParams(String path, Map<String, Object> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path);
        if (queryParams != null) {
            queryParams.forEach(builder::queryParam);
        }
        return builder.build().toUriString();
    }

    // GET Requests with query params
    public void getString(String path, Map<String, Object> queryParams) {
        webClient.get()
                .uri(buildUrlWithParams(path, queryParams))
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(WebClientResponseException.class, this::handleHttpError)
                .block();
    }

    public String getStringResponse(String path, Map<String, Object> queryParams) {
        return webClient.get()
                .uri(buildUrlWithParams(path, queryParams))
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, this::handleHttpError)
                .block();
    }

    public <T> T getJsonResponse(String path, Map<String, Object> queryParams, Class<T> responseType) {
        return webClient.get()
                .uri(buildUrlWithParams(path, queryParams))
                .retrieve()
                .bodyToMono(responseType)
                .doOnError(WebClientResponseException.class, this::handleHttpError)
                .block();
    }

    private void handleHttpError(WebClientResponseException ex) {
        // Handle the error accordingly
        throw new RuntimeException("HTTP error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString(StandardCharsets.UTF_8));
    }

    public Flux<String> getStringFluxResponse(String path, Map<String, Object> queryParams) {
        return webClient.get()
                .uri(buildUrlWithParams(path, queryParams))
                .retrieve()
                .bodyToFlux(String.class)
                .doOnError(WebClientResponseException.class, this::handleHttpError);
    }

    public <T> Flux<T> getJsonFluxResponse(String path, Map<String, Object> queryParams, Class<T> responseType) {
        return webClient.get()
                .uri(buildUrlWithParams(path, queryParams))
                .retrieve()
                .bodyToFlux(responseType)
                .doOnError(WebClientResponseException.class, this::handleHttpError);
    }

    // POST Requests with JSON body and reactive Flux response
    public <T, R> Flux<R> postJsonFluxResponse(String path, T requestBody, Class<R> responseType) {
        return webClient.post()
                .uri(path)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(responseType)
                .doOnError(WebClientResponseException.class, this::handleHttpError);
    }

    // POST Request with JSON body and synchronous JSON response
    public <T, R> R postJsonResponse(String path, T requestBody, Class<R> responseType) {
        return webClient.post()
                .uri(path)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .doOnError(WebClientResponseException.class, this::handleHttpError)
                .block();
    }
}