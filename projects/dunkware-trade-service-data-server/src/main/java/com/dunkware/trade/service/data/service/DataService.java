package com.dunkware.trade.service.data.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dunkware.trade.service.data.service.message.StreamMessageProcessor;


//@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node.trace, com.dunkware.trade.service.data.service")
//@ComponentScan
//@EnableTransactionManagement

//@EnableAutoConfiguration(exclude={ MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.dunkware.net.cluster.node.trace, com.dunkware.trade.service.data.service")

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableIntegration 
@EnableBinding(StreamMessageProcessor.class)
public class DataService {

	public static void main(String[] args) {
		SpringApplication.run(DataService.class, args);
		
	}

}
 