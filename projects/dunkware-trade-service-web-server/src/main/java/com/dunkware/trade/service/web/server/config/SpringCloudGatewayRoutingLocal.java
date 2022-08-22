package com.dunkware.trade.service.web.server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class SpringCloudGatewayRoutingLocal {

	/*
	 * @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	 * return builder.routes() .route("stream",
	 * r->r.path("/stream/**").uri("http://localhost:8086/i")).build(); //static
	 * routing //.route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
	 * //dynamic routing //.build(); }
	 */
    

	
	  @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	  return builder.routes() .route("stream",
	  r->r.path("/stream/**").uri("http://172.16.16.55:32100/i")).build(); //static
	  
	  ///trade/web/* to http://172.16.16.55:31890
	 // routing //.route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
	  //dynamic routing //.build(); }
	  }
	 
}