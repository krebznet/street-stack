package com.dunkware.trade.service.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.dunkware")
public class StreamServer {

	public static void main(String[] args) {
		System.out.println("what the fuck!");
		SpringApplication.run(StreamServer.class, args);
	}

}
 	