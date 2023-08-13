package com.dunkware.trade.net.service.streamstats.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.dunkware")
@EnableAutoConfiguration()
//@EnableBinding(StreamProcessor.class)
public class StreamStatsServer {



	public static void main(String[] args) {
		SpringApplication.run(StreamStatsServer.class, args);
	}
	
	// okay need this here 
	// RocLastSma5x7min
	

}
