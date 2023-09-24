package com.dunkware.common.kafka;

public class DKafkaException extends Exception {

	
	public DKafkaException(String s) { 
		super(s);
		
	}
	
	public DKafkaException(String s, Throwable t) { 
		super(s,t);
	}
}
