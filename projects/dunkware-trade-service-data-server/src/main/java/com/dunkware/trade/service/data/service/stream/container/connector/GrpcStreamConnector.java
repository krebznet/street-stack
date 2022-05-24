package com.dunkware.trade.service.data.service.stream.container.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.net.proto.netstream.GNetClientConnectRequest;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerConnector;
import com.dunkware.xstream.net.core.util.GNetProto;

import io.grpc.stub.StreamObserver;

public class GrpcStreamConnector implements StreamContainerConnector, StreamObserver<GNetClientMessage> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private StreamObserver<GNetServerMessage> responseObserver;
	private LinkedBlockingQueue<GNetClientMessage> messageQueue = new LinkedBlockingQueue<GNetClientMessage>();
	private GNetClientConnectRequest handshake = null;
	private boolean connected = true; 
	
	public GrpcStreamConnector(StreamObserver<GNetServerMessage> responseObserver) { 
		this.responseObserver = responseObserver;
		
	}
	
	@Override
	public void onNext(GNetClientMessage value) {
		System.out.println("Grpc Net client message received ");
		if(GNetProto.isClientConnectMessage(value)) { 
			handshake = value.getConnectRequest();
		} else { 
			messageQueue.add(value);	
		}
		
	}

	@Override
	public void onError(Throwable t) {
		logger.error("Error in GrpcStreamConnector onError " + t.toString(),t);
		// notify 
		
	}

	@Override
	public void onCompleted() {
		connected = false; 
	}

	@Override
	public BlockingQueue<GNetClientMessage> getMessageQueue() {
		return messageQueue;
	}

	@Override
	public GNetClientConnectRequest getHandshake() {
		return handshake;
	}

	@Override
	public void sendMessage(GNetServerMessage message)  {
		if(!connected) { 
			logger.error("trying to send message but not connected ");
		}
		try {
			responseObserver.onNext(message);
		} catch (Exception e) {
			logger.error("exception sending message does this mean initaite close event " + e.toString());
		}
		
		
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public void close() {
		onCompleted();
		connected = false;
	}
	
	
	
	
	
	

	
}
