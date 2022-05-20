package com.dunkware.trade.service.data.service.stream.container.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.net.proto.netstream.GNetClientConnect;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerConnector;

import io.grpc.stub.StreamObserver;

public class GrpcStreamConnector implements StreamContainerConnector, StreamObserver<GNetClientMessage> {

	private StreamObserver<GNetServerMessage> responseObserver;
	private LinkedBlockingQueue<GNetClientMessage> messageQueue = new LinkedBlockingQueue<GNetClientMessage>();
	
	public GrpcStreamConnector(StreamObserver<GNetServerMessage> responseObserver) { 
		this.responseObserver = responseObserver;
		
	}
	
	@Override
	public void onNext(GNetClientMessage value) {
		messageQueue.add(value);
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		// TODO Auto-generated method stub
		// means they closed connection;
	}

	@Override
	public BlockingQueue<GNetClientMessage> getMessageQueue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GNetClientConnect getHandshake() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(GNetServerMessage message) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
