package com.dunkware.trade.service.stream.server.messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.json.message.StreamMessage;

@Service
@EnableIntegration 
public class StreamMessageService {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BlockingQueue<StreamMessage> messages = new LinkedBlockingQueue<StreamMessage>();
	
	public void sendMessage(StreamMessage message) { 
		this.messages.add(message);
	}
	
	 @Bean
	 public Supplier<StreamMessage> supplyStreamMessage(){
		 
		    Supplier<StreamMessage> streamMessageSupplier = () -> {
		      while(true) { 
		    	  try {
		    		 
		    		  StreamMessage message = messages.take();
		        	  return message;
				} catch (Exception e) {
					logger.error("Error Sending Stream Message " + e.toString());
				}
		    	 
		      }
		    
		    };
		    return streamMessageSupplier;
	 }

	
	

}
