package com.dunkware.trade.service.web.server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin("*")
@Profile("local")
public class SpringCloudGatewayRoutingLocal {

	/*      c
	 * @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
	 * return builder.routes() .route("stream",
	 * r->r.path("/stream/**").uri("http://localhost:8086/i")).build(); //static
	 * routing //.route("orderId", r->r.path("/order/**").uri("lb://ORDER-SERVICE"))
	 * //dynamic routing //.build(); }
	 */

	 @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
		  
		  return builder.routes().route("stream", r -> r.path("/stream/**").uri("http://127.0.0.1:8086")) // static
					.route("trade", r -> r.path("/trade/**").uri("http://127.0.0.1:8032"))
					.route("tick", r -> r.path("/tick/**").uri("http://172.16.16.55:31890"))
					.route("feed", r -> r.path("/feed/**").uri("http://172.16.16.55:31890"))
						.build();
		  }
	 
			/*
			 * @Bean public RouteLocator configureRoute(RouteLocatorBuilder builder) {
			 * return builder.routes().route("stream", r ->
			 * r.path("/stream/**").uri("http://localhost:8086")) // static .route("trade",
			 * r -> r.path("/trade/**").uri("http://127.0.0.1:8032")).route("mock", r->
			 * r.path("/mock/**").uri("http://localhost:8071")).build(); }
			 * 
			 */

}