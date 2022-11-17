package com.dunkware.trade.service.data.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = "com.dunkware")
@EnableMongoRepositories()
public class DataServer {

	public static void main(String[] args) {
		SpringApplication.run(DataServer.class, args);
	}

}
 	