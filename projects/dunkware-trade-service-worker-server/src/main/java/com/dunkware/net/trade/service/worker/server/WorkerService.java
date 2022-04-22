package com.dunkware.net.trade.service.worker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node, com.dunkware.trade.service.worker.server, com.dunkware.trade.service.stream.worker")

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@EnableBinding(StreamProcessor.class)
public class WorkerService {

	public static void main(String[] args) {
		SpringApplication.run(WorkerService.class, args);
	}

}
