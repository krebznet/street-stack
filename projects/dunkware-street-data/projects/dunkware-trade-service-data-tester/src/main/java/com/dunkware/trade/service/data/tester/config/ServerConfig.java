package com.dunkware.trade.service.data.tester.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
	
	private String mongoURL; 
	
	private String kafkaBrokers;
	
	private String signalTopic;

	public String getMongoURL() {
		return mongoURL;
	}

	public void setMongoURL(String mongoURL) {
		this.mongoURL = mongoURL;
	}

	public String getKafkaBrokers() {
		return kafkaBrokers;
	}

	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}

	public String getSignalTopic() {
		return signalTopic;
	}

	public void setSignalTopic(String signalTopic) {
		this.signalTopic = signalTopic;
	} 
	
	

}
