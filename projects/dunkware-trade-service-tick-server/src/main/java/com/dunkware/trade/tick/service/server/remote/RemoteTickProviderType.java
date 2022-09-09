package com.dunkware.trade.tick.service.server.remote;

import com.dunkware.trade.tick.model.provider.TickProviderSpec;

public class RemoteTickProviderType extends TickProviderSpec {

	private String host; 
	private int port; 
	private String key;
	
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	} 
	
	
}
