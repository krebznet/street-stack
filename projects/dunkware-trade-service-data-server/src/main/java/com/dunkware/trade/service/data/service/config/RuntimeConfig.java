package com.dunkware.trade.service.data.service.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

@Service
public class ConfigService {
	
	@Value("${gclient.streamservice.grpc}")
	private String streamServiceGrpcServer;
	
	@Value("${mongo.url}")
	private String mongoURL; 
	
	@Value("${mongo.db}")
	private String mongoDatabase; 
	
	private DExecutor executor; 
	
	@PostConstruct
	private void postConstruct() { 
		executor = new DExecutor(10);
	}
	
	public DExecutor getExecutor() { 
		return executor;
	}
	public String getStreamServiceGrpcServer() { 
		return streamServiceGrpcServer;
	}

	public String getMongoURL() {
		return mongoURL;
	}

	public String getMongoDatabase() {
		return mongoDatabase;
	}
	

}
