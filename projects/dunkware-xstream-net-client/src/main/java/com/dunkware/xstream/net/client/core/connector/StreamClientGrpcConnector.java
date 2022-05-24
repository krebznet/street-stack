package com.dunkware.xstream.net.client.core.connector;

import com.dunkware.net.proto.data.service.GDataServiceGrpc;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.net.proto.stream.service.GStreamServiceGrpc;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;
import com.dunkware.xstream.net.client.connector.StreamClientGrpcConnectorType;
import com.dunkware.xstream.net.client.core.StreamClientProto;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class StreamClientGrpcConnector implements StreamClientConnector, StreamObserver<GNetServerMessage> {

	private StreamClientGrpcConnectorType myType; 
	
	private ManagedChannel channel; 
	
	private GDataServiceGrpc.GDataServiceStub stub = null;
	
	private StreamObserver<GNetClientMessage> request = null;
	
	@Override
	public void connect(StreamClientConnectorType config) throws StreamClientException {
		try {
			myType = (StreamClientGrpcConnectorType)config;
			String url = myType.getHost() + ":" + myType.getPort();
			  
			
			   channel = ManagedChannelBuilder.forAddress("localhost",8091).usePlaintext().build();
				stub = GDataServiceGrpc.newStub(channel);
				request = stub.streamClient(this);
				 // create the handshake shit; 
				GNetClientMessage connect = StreamClientProto.connectRequest("testfuck", "us_equity");
				Thread.sleep(1000);
				request.onNext(connect);
		} catch (Exception e) {
			throw new StreamClientException("Exception casting grpc connector type " + e.toString());
		}
		
		
	}

	@Override
	public void sendMessage(GNetClientMessage message) {
		try {
			request.onNext(message);
		} catch (Exception e) {
			// todo here; 
			System.err.println("Exception sending message " + e.toString());
		}
		
	}

	@Override
	public Object consume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onNext(GNetServerMessage value) {
		System.out.println("server message receieved ");
		
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("GrpcClientCOnnector on error " + t.toString());
		
	}

	@Override
	public void onCompleted() {
		System.out.println("GrpcClientCOnnector on completed");
	}
	
	
	
	

	
}
