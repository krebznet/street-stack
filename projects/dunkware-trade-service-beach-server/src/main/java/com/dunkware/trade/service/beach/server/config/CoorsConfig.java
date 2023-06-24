package com.dunkware.trade.service.beach.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration()
public class CoorsConfig {
	

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	//configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:3000", "https://dunkstreet.com", "https://api.dunkstreet.com","https://gateway.dunkstreet.com", "http://testrock1.dunkware.net:32369"));
    	configuration.setAllowedOrigins(Arrays.asList("*"));
    	configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS"));
    	configuration.setAllowedHeaders(Arrays.asList("*"));
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }

}
