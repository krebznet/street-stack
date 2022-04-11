package com.dunkware.trade.service.web.server.grpc.client;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.net.proto.stream.GAutCompleteRequest;
import com.dunkware.net.proto.stream.GAutoCompleteResponse;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceBlockingStub;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc.GStreamServiceStub;
import com.dunkware.trade.service.web.server.config.RuntimeConfig;
import com.dunkware.trade.service.web.server.logging.LogService;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@Service
public class GrpcWebServiceStreamServiceClient {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LogService logService;
	
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
		     channel = ManagedChannelBuilder.forTarget(config.serviceStreamGRPC()).usePlaintext().build();
		     asyncSub =  GStreamServiceGrpc.newStub(channel);
		     blockingStub = GStreamServiceGrpc.newBlockingStub(channel);
		     connected = true; 
		     // okay this is the asyncSub we need for streaming
		    
		} catch (Exception e) {
			//logger.error(DataMarkers.getServiceMarker(),"Exception connecting to Stream Service GRPC Server" + e.toString(),e);
			System.exit(-1);
		}
		
	}
	
	
	/** Nice this will send a messge to the stream service **/
	public StreamObserver<GAutCompleteRequest> autoCompleteSearch(
			StreamObserver<GAutoCompleteResponse> responseObserver) { 
		
		asyncSub.autoCompleteSearch(responseObserver);// this does what?
		
		return null;
	}
	

}
