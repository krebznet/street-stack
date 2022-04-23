package com.dunkware.trade.service.web.server.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.web.server.config.RuntimeConfig;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

@Service
public class GrpcWebServiceChannels {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RuntimeConfig config;
	
	private Channel streamChannel;
	
	private boolean connected = false; 

	@PostConstruct
	public void load() { 
		try {
		     streamChannel = ManagedChannelBuilder.forTarget(config.serviceStreamGRPC()).usePlaintext().build();
		     connected = true; 
		    
		} catch (Exception e) {
			logger.error("Exception creating stream service channel to " + config.serviceStreamGRPC() + " " + e.toString(),e);
		}
		
	}
	

	
	public Channel getStreamServiceChannel() { 
		return streamChannel;
	}
}
