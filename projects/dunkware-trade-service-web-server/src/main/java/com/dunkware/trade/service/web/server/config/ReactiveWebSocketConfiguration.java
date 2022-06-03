package com.dunkware.trade.service.web.server.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import com.dunkware.trade.service.web.server.websocket.NetServiceAdapter;

@Configuration
public class ReactiveWebSocketConfiguration {


    @Autowired
    private NetServiceAdapter webSocketHandler;

    @Bean
    public HandlerMapping webSocketHandlerMapping() {
        Map<String, NetServiceAdapter> urlMap = new HashMap<>();
        urlMap.put("/websocket", webSocketHandler);

        Map<String, CorsConfiguration> corsConfigurationMap =
                new HashMap<>();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        corsConfigurationMap.put("/websocket", corsConfiguration);

        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(urlMap);
        return handlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
