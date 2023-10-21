package com.dunkware.trade.service.beach.protocol.broker;

import com.dunkware.common.util.json.DJson;

public class AddBrokerReq {
	
	private String name; 
	private String host; 
	private int port; 
	private String type;
	
	public static void main(String[] args) {
		AddBrokerReq req = new AddBrokerReq();
		req.setName("DLK Paper");;
		req.setPort(7497);
		req.setHost("172.17.3.42");
		req.setType("TWS");
		try {
			System.out.println(DJson.serializePretty(req));
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
