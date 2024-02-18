package com.dunkware.trade.net.data.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = "com.dunkware")

@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class DataService {

    public static void main(String[] args) {
        SpringApplication.run(DataService.class, args);
    }

    
    // Founder & Chief Software Architect
    
    
    	// Duncan i I get to write my own extendable plugins for Dunkware 
   
}

// Dunkware Studio -> One Project For Things 


// Simple Fuckin Search Dunkware Search (Schwab)Alpha Breakout 1

	// Dunkware Develoepr 				//--- 
					