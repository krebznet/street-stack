package com.dunkware.baked.core;

import com.dunkware.baked.core.user.User;

import javax.security.auth.login.LoginException;
import java.net.URI;
import java.net.http.HttpHeaders;

public class Baked {

    private static  Baked instance;

    private boolean loggedIn = false;

    public static Baked get() {
        if (instance == null) {
            instance = new Baked();
        }
        return instance;
    }

        public User login(String baseUrl, String username, String password) throws LoginException {
            String loginUrl = baseUrl + "/api/gateway/login"; // Base login endpoint

            // Construct the URL with query parameters
            URI uri = UriComponentsBuilder.fromHttpUrl(loginUrl)
                    .queryParam("username", username)
                    .queryParam("password", password)
                    .build().toUri();

            // Optional: Add headers (if needed)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            // Build the HTTP request
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            try {
                // Send GET request to login endpoint
                ResponseEntity<LoginResponse> responseEntity = restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        requestEntity,
                        LoginResponse.class
                );

                // Check if login is successful
                if (responseEntity.getBody() != null) {
                    return responseEntity.getBody(); // Return the full LoginResponse object
                } else {
                    throw new LoginException("Login failed. No data returned.");
                }

            } catch (HttpClientErrorException e) {
                throw new LoginException("Error during login: " + e.getMessage(), e);
            }
        }




    public boolean isLoggedIn() {
        return loggedIn;
    }


}
