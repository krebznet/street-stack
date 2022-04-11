package com.dunkware.trade.service.web.server.grpc.client;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.net.proto.stream.service.GStreamServiceGrpc;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceBlockingStub;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceStub;
import com.dunkware.trade.service.web.server.config.RuntimeConfig;
import com.dunkware.trade.service.web.server.logging.LogService;

import io.grpc.ManagedChannel;

@Service
public class GrpcWebServiceStreamServiceClient {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private LogService logService;
	
	@Autowired
	private RuntimeConfig configService;
	
	private GStreamServiceBlockingStub blockingStub;
	private GStreamServiceStub asyncSub; 
	
	private ManagedChannel channel;
	
	private boolean connected = false; 
	
	//TODO: see what we need to hook you GSTreamServiceClient
	@PostConstruct
	public void load() { 
		try {
		    // channel = ManagedChannelBuilder.forTarget(configService.getStreamServiceGrpcServer()).usePlaintext().build();
		     asyncSub =  GStreamServiceGrpc.newStub(channel);
		     blockingStub = GStreamServiceGrpc.newBlockingStub(channel);
		     connected = true; 
		     // okay this is the asyncSub we need for streaming
		    
		} catch (Exception e) {
			//logger.error(DataMarkers.getServiceMarker(),"Exception connecting to Stream Service GRPC Server" + e.toString(),e);
			System.exit(-1);
		}
		
	}
	
	

}
