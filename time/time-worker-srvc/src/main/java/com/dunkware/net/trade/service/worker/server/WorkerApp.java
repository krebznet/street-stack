package com.dunkware.net.trade.service.worker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication(scanBasePackages = "com.dunkware")
@EnableWebFlux
@EnableAutoConfiguration(exclude={WebMvcAutoConfiguration.class,MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, CassandraAutoConfiguration.class})

//@EnableBinding(StreamProcessor.class)
public class WorkerApp {



	public static void main(String[] args) {
		SpringApplication.run(WorkerApp.class, args);
	}
	
	// okay need this here 
	// RocLastSma5x7min
	

}
