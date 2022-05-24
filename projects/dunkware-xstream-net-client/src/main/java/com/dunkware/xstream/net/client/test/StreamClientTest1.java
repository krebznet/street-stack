package com.dunkware.xstream.net.client.test;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.net.client.StreamClient;
import com.dunkware.xstream.net.client.StreamClientFactory;
import com.dunkware.xstream.net.client.StreamClientInput;
import com.dunkware.xstream.net.client.connector.StreamClientGrpcConnectorType;

public class StreamClientTest1 {
	
	public static void main(String[] args) {
		new StreamClientTest1();
		while(true) { 
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private StreamClient client; 
	private DExecutor executor; 
	public StreamClientTest1() {
		executor = new DExecutor(5);
		client = StreamClientFactory.create();
		StreamClientGrpcConnectorType type = new StreamClientGrpcConnectorType();
		type.setHost("localhost");
		type.setPort(8091);
		try {
			StreamClientInput input = new StreamClientInput();
			input.setConnectorType(type);
			input.setExecutor(executor);
			client.connect(input);
		} catch (Exception e) {
			System.err.println("Client Connect Exception " + e.toString());
			return;
		}
		
	}

}
