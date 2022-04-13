package com.dunkware.trade.service.data.service.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

@Service
public class RuntimeConfig {
	
	@Value("${mongo.url}")
	private String mongoURL; 
	
	@Value("${mongo.db}") 
	private String mongoDatabase; 
	
	@Value("${service.stream.url.http}")
	private String streamServiceHTTPURL; 
	
	@Value("${service.stream.url.grcp}")
	private String streamServiceGRPCURL;
	
	private DExecutor executor; 
	
	@PostConstruct
	private void postConstruct() { 
		executor = new DExecutor(10);
	}
	
	public DExecutor getExecutor() { 
		return executor;
	}
	
	public String getMongoURL() {
		return mongoURL;
	}

	public String getMongoDatabase() {
		return mongoDatabase;
	}

	public String getStreamServiceHTTPURL() {
		return streamServiceHTTPURL;
	}

	public String getStreamServiceGRPCURL() {
		return streamServiceGRPCURL;
	}
	
	
	

}
