package com.dunkware.trade.tick.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={JmxAutoConfiguration.class})
public class TickService {

	public static void main(String[] args) {
		SpringApplication.run(TickService.class, args);
	}

}
