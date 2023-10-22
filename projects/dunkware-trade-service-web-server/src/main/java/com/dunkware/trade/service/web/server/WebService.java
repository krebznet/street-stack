package com.dunkware.trade.service.web.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;


@EnableWebFlux
@SpringBootApplication()
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class WebService {

    public static void main(String[] args) {
        SpringApplication.run(WebService.class, args);
    }

}
