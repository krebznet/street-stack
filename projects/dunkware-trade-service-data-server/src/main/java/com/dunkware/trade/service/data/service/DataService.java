package com.dunkware.trade.service.data.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.dunkware.trade.service.data.service.message.MessageProcessor;


@SpringBootApplication
@EnableBinding(MessageProcessor.class)
public class DataService {

	public static void main(String[] args) {
		SpringApplication.run(DataService.class, args);
		
	}

}
 