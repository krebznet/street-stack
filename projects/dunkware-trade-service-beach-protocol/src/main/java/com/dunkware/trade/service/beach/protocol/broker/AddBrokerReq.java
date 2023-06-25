package com.dunkware.trade.service.beach.protocol.broker;

import com.dunkware.common.util.json.DJson;

public class AddBrokerReq {
	
	private String name; 
	private String host; 
	private int port; 
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static void main(String[] args) {
		AddBrokerReq req = new AddBrokerReq();
		req.setHost("127.0.0.1");
		req.setType("Interactive Brokers");
		req.setPort(3);
		req.setName("Paper Broker 1");
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
