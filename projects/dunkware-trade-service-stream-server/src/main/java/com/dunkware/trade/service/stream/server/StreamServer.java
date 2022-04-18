package com.dunkware.trade.service.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.integration.config.EnableIntegration;


@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node.trace, com.dunkware.trade.service.stream.server")

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableIntegration 
public class StreamServer {

	public static void main(String[] args) {
		SpringApplication.run(StreamServer.class, args);
	}

}
