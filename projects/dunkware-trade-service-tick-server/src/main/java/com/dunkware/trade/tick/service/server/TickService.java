package com.dunkware.trade.tick.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.spring,com.dunkware.net.cluster.node, com.dunkware.trade.tick.service.server")
public class TickService {

	public static void main(String[] args) {
		SpringApplication.run(TickService.class, args);
	}

}
