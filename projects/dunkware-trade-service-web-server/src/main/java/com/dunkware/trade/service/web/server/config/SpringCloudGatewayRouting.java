package com.dunkware.trade.service.web.server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
       return builder.routes()
      .route("stream", r->r.path("/stream/**").uri("http://172.16.16.55:31750/i")).build();
      //static routing
      //.route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE")) //dynamic routing
      //.build();
    }
}