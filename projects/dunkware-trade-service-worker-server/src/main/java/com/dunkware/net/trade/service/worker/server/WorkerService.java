package com.dunkware.net.trade.service.worker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.dunkware")

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, CassandraAutoConfiguration.class})

//@EnableBinding(StreamProcessor.class)
public class WorkerService {



	public static void main(String[] args) {
		SpringApplication.run(WorkerService.class, args);
	}
	
	// okay need this here 
	// RocLastSma5x7min
	

}
