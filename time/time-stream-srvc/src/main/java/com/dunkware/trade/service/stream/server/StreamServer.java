package com.dunkware.trade.service.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EntityScan(basePackages = {"com.dunkware.trade.service.stream.server,com.dunkware.time.script.mod"})
@SpringBootApplication(scanBasePackages = "com.dunkware")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, CassandraAutoConfiguration.class})
public class StreamServer {

	public static void main(String[] args) {
		SpringApplication.run(StreamServer.class, args);
	}

}
 	