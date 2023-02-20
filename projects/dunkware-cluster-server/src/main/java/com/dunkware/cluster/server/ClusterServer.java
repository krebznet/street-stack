package com.dunkware.cluster.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication(scanBasePackages="com.dunkware.net.cluster.spring,com.dunkware.net.cluster.server")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@EnableBinding(StreamProcessor.class)

public class ClusterServer {

	public static void main(String[] args) {
		SpringApplication.run(ClusterServer.class, args);
	}

}
