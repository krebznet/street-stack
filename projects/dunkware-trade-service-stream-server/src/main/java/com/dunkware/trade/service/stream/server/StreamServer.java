package com.dunkware.trade.service.stream.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.dunkware")
public class StreamServer {

	public static void main(String[] args) {
		SpringApplication.run(StreamServer.class, args);
	}

}
 	