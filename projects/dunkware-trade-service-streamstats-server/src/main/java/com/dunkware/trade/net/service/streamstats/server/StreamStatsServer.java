package com.dunkware.trade.net.service.streamstats.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.dunkware")
public class StreamStatsServer {

	public static  int batchsize = 2000;


	public static void main(String[] args) {
		System.setProperty("batchsize", "2500");
		for (String string : args) {
			if(string.contains("--batchsize")) { 
				String sub = string.substring(12,string.length());
				StreamStatsServer.batchsize = Integer.valueOf(sub);
				System.setProperty("batchsize", sub);
			}
		}
		SpringApplication.run(StreamStatsServer.class, args);
	}
	
	// okay need this here 
	// RocLastSma5x7min
	

}
