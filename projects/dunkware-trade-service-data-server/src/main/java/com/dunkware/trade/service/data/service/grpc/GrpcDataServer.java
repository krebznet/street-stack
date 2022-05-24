package com.dunkware.trade.service.data.service.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.proto.data.service.GDataServiceGrpc.GDataServiceImplBase;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerService;
import com.dunkware.trade.service.data.service.stream.container.connector.GrpcStreamConnector;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;



/**
 * This is the start of our GRPC Service API that will accept 
 * GEntitySignalSearchRequest that contains a GEntitySignalQuery
 * GEntityS
 * @author duncankrebs
 *
 */
@GrpcService()
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrpcDataServer extends GDataServiceImplBase {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StreamContainerService containerService; 
	
	
	
	
	@Override
	public StreamObserver<GNetClientMessage> streamClient(StreamObserver<GNetServerMessage> responseObserver) {
		logger.debug("Receieved Stream Client Request");
		GrpcStreamConnector connector = new GrpcStreamConnector(responseObserver);
		
		class DelayNewConnector extends Thread { 
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				containerService.newConnector(connector);		
			}
		};
		DelayNewConnector con = new DelayNewConnector();
		con.start();
		
		
		return connector;
	}
	
	
	

	
	

	

}
