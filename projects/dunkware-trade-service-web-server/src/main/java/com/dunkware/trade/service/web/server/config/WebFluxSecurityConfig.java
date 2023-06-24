package com.dunkware.trade.service.web.server.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("dunkware")
                .password("{noop}dunkStreet@2022")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.csrf().disable();
        http.cors();
        http
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
  

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	//configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:3000", "https://dunkstreet.com", "https://api.dunkstreet.com","https://gateway.dunkstreet.com", "http://testrock1.dunkware.net:32369"));
    	configuration.setAllowedOrigins(Arrays.asList("*"));
    	configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD","OPTIONS"));
    	configuration.setAllowedHeaders(Arrays.asList("*"));
    	configuration.setAllowCredentials(true);
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }
    
    
}