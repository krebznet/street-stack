package com.dunkware.trade.service.data.service.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface MessageProcessor {

  public static final String MESSAGES_IN = "stream-messages";
	  

	  @Input(MESSAGES_IN)
	  SubscribableChannel sourceOfMessages();

}
