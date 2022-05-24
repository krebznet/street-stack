package com.dunkware.xstream.net.client.core.connector;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.dunkware.net.proto.data.service.GDataServiceGrpc;
import com.dunkware.net.proto.netstream.GNetClientConnectResponse;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.xstream.net.client.StreamClientConnector;
import com.dunkware.xstream.net.client.StreamClientException;
import com.dunkware.xstream.net.client.connector.StreamClientConnectorType;
import com.dunkware.xstream.net.client.connector.StreamClientGrpcConnectorType;
import com.dunkware.xstream.net.client.core.StreamClientProto;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.netty.channel.ChannelOption;

public class StreamClientGrpcConnector implements StreamClientConnector, StreamObserver<GNetServerMessage> {

	private StreamClientGrpcConnectorType myType; 
	
	private ManagedChannel channel; 
	
	private GDataServiceGrpc.GDataServiceStub stub = null;
	
	private StreamObserver<GNetClientMessage> request = null;
	
	private BlockingQueue<GNetServerMessage> serverMessageQueue = new LinkedBlockingQueue<GNetServerMessage>();
	
	private GNetClientConnectResponse connectResponse = null;
	
	private boolean connected = true;
	
	
	
	

	@Override
	public void connect(StreamClientConnectorType config, String ident, String stream) throws StreamClientException {
		try {
			myType = (StreamClientGrpcConnectorType)config;
			String url = myType.getHost() + ":" + myType.getPort();
			  
			
			   channel = ManagedChannelBuilder.forAddress("localhost",8091).usePlaintext().keepAliveTime(5,TimeUnit.MINUTES).keepAliveTimeout(5, TimeUnit.MINUTES).enableRetry().keepAliveWithoutCalls(true).build();
				stub = GDataServiceGrpc.newStub(channel);
			
				request = stub.streamClient(this);
				 // create the handshake shit; 
				GNetClientMessage connect = StreamClientProto.connectRequest("testfuck", "us_equity");
				Thread.sleep(1000);
				request.onNext(connect);
				Thread sender = new Thread() { 
					
					public void run() { 
						while(!interrupted()) { 
							try {
								GNetClientMessage connect = StreamClientProto.connectRequest("testfuck", "us_equity");
								request.onNext(connect);
								Thread.sleep(500);
							} catch (Exception e) {
								e.printStackTrace();
								// TODO: handle exception
							}
						}
					}
				};
				sender.start();
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
	public BlockingQueue<GNetServerMessage> getServerMessageQueue() {
		return serverMessageQueue;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}


	@Override
	public GNetClientConnectResponse getConnectResponse()  throws StreamClientException {
		int count = 0;
		while(connectResponse == null) { 
			try {
				Thread.sleep(500);
				count++;
				if(count > 200) { 
					throw new StreamClientException("Timeout on Get Connect response");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return connectResponse;
	}

	@Override
	public void onNext(GNetServerMessage value) {
		if(StreamClientProto.isConnectionResponse(value)) { 
			this.connectResponse = value.getConnectResponse();
		}
		serverMessageQueue.add(value);
		
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
