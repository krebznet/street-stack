package com.dunkware.trade.tick.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, CassandraAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.dunkware")
public class TickService {

	public static void main(String[] args) {
		SpringApplication.run(TickService.class, args);
	}

}
