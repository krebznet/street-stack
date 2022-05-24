package com.dunkware.xstream.net.client.connector;

public class StreamClientGrpcConnectorType extends StreamClientConnectorType  {

	private String host; 
	private int port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	} 
	
	
}
