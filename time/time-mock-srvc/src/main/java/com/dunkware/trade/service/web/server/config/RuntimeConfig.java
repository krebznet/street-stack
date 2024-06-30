package com.dunkware.trade.service.web.server.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.utils.core.concurrent.DunkExecutor;

@Service
public class RuntimeConfig {

	@Value("${mongo.url}")
	private String mongoURL; 
	
	@Value("${mongo.db}") 
	private String mongoDatabase; 
	
	@Value("${service.stream.url.http}")
	private String serviceStreamHTTP; 
	
	@Value("${service.stream.url.grpc}")
	private String serviceStreamGRPC;
	
	private DunkExecutor executor; 
	
	@PostConstruct
	private void postConstruct() { 
		executor = new DunkExecutor(10);
	}
	
	public DunkExecutor excutor() { 
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
