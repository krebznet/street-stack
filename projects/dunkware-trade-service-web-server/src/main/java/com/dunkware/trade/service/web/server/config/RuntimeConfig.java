package com.dunkware.trade.service.web.server.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.dunkware.common.util.executor.DExecutor;

public class RuntimeConfig {

	@Value("${mongo.url}")
	private String mongoURL; 
	
	@Value("${mongo.db}") 
	private String mongoDatabase; 
	
	@Value("${service.stream.url.http")
	private String serviceStreamHTTP; 
	
	@Value("$service.stream.url.grpc")
	private String serviceStreamGRPC;
	
	private DExecutor executor; 
	
	@PostConstruct
	private void postConstruct() { 
		executor = new DExecutor(10);
	}
	
	public DExecutor excutor() { 
		return executor;
	}
	
	public String mongoURL() {
		return mongoURL;
	}

	public String mongoDatabase() {
		return mongoDatabase;
	}

	public String serviceStreamHTTP() {
		return serviceStreamHTTP;
	}

	public String serviceStreamGRPC() {
		return serviceStreamGRPC;
	}

	
	
}
