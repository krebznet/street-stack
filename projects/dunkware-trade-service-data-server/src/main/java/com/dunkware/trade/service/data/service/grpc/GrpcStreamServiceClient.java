package com.dunkware.trade.service.data.service.grpc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.net.proto.stream.GStreamSpecsRequest;
import com.dunkware.net.proto.stream.GStreamSpecsResponse;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceBlockingStub;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceStub;
import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.util.DataMarkers;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class GrpcStreamServiceClient {

private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RuntimeConfig config;
	
	private GStreamServiceBlockingStub blockingStub;
	private GStreamServiceStub asyncSub; 
	
	private ManagedChannel channel;
	
	
	
	private boolean connected = false; 
	
	//TODO: see what we need to hook you GSTreamServiceClient
	@PostConstruct
	public void load() { 
		try {
		     channel = ManagedChannelBuilder.forTarget(config.getStreamServiceGRPCURL()).usePlaintext().build();
		     asyncSub =  GStreamServiceGrpc.newStub(channel);
		     blockingStub = GStreamServiceGrpc.newBlockingStub(channel);
		     connected = true; 
		     // okay this is the asyncSub we need for streaming
		    
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(),"Exception connecting to Stream Service GRPC Server" + e.toString(),e);
			System.exit(-1);
		}
		
	}
	
	public GStreamSpecsResponse streamSpecs() throws Exception {
		try {
			GStreamSpecsRequest request = GStreamSpecsRequest.newBuilder().setHello(1).build();
			
			GStreamSpecsResponse response = blockingStub.streamSpecs(request);
			return response;
		} catch (Exception e) {
			logger.error(DataMarkers.getServiceMarker(), "Exception getting stream specs in client " + e.toString());
			throw e;
		}
		
		
	}
	
	
	
	
	

		
	
	
	
	
	
}
