	package com.dunkware.trade.service.web.server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("LocalTrade")
public class SpringCloudGatewayRoutingLocalTrade {

	/*
	 * @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	 * return builder.routes() .route("stream",
	 * r->r.path("/stream/**").uri("http://localhost:8086/i")).build(); //static
	 * routing //.route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
	 * //dynamic routing //.build(); }
	 */
    

	
	  @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	  
	  return builder.routes().route("stream", r -> r.path("/stream/**").uri("http://172.16.16.55:32100")) // static
				.route("trade", r -> r.path("/trade/**").uri("http://localhost:8032"))
				.route("tick", r -> r.path("/tick/**").uri("http://172.16.16.55:31890"))
				.route("feed", r -> r.path("/feed/**").uri("http://172.16.16.55:31890"))
					.build();
	  }
	 
}