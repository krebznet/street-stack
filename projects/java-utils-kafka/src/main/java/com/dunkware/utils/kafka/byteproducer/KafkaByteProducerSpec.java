package com.dunkware.utils.kafka.byteproducer;

import java.util.Properties;

public class KafkaByteProducerSpec {

	public static Builder builder() { 
		return new Builder();
	}
	
	
	public static class Builder { 
		
		
		
	}
	
	private String brokers; 
	private String topics; 
	private String identifier = null;
	private Properties properties = new Properties();
	
	public KafkaByteProducerSpec() { 
		
	}

	public String getBrokers() {
		return brokers;
	}

	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
	
	
	
	
	
	
}
