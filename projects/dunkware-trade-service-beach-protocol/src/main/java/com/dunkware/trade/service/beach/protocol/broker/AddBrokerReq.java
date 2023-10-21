package com.dunkware.trade.service.beach.protocol.broker;

import com.dunkware.common.util.json.DJson;

public class AddBrokerReq {
	
	private String name; 
	private String host; 
	private int port; 
	private String type;
	
	public static void main(String[] args) {
		AddBrokerReq req = new AddBrokerReq();
		req.setName("name");;
		req.setPort(3);
		req.setHost("hostme");
		req.setType("type me");
		try {
			System.out.println(DJson.serialize(req));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
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
	
	
	
	
	

}
