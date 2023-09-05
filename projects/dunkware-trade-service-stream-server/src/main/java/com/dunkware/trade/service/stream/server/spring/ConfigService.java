package com.dunkware.trade.service.stream.server.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
	
	

	@Value("${kafka.brokers}")
	private String kafkaBrokers; 
	
	@Value("${tick.service.endpoint}")
	private String tickServiceURL;
	
	@Value("${streams.schedule.enable}")
	private boolean scheduleStreams;
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	
	
	public String getTickServiceURL() {
		return tickServiceURL;
	}
	public boolean isScheduleStreams() {
		return scheduleStreams;
	}

	
	
	

}
