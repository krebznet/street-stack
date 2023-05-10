package com.dunkware.trade.service.beach.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration (exclude = {  DataSourceAutoConfiguration.class })
@SpringBootApplication
public class BeachApp {

	public static void main(String[] args) {
		SpringApplication.run(BeachApp.class, args);
	}

}
