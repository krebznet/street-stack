package com.dunkware.trade.tick.service.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;


@EnableAutoConfiguration(exclude={JmxAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node.trace, com.dunkware.trade.tick.service.server")
public class TickService {

	public static void main(String[] args) {
		SpringApplication.run(TickService.class, args);
	}

}
