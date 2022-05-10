package com.dunkware.trade.service.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node,com.dunkware.net.cluster.spring,com.dunkware.trade.service.stream.server")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StreamServer {

	public static void main(String[] args) {
		SpringApplication.run(StreamServer.class, args);
	}

}
