package com.dunkware.trade.service.stream.resources;

import com.dunkware.common.util.json.DJson;

public class SignalResource {
	
	private long id; 
	private String identifier;
	private String name;
	
	public static void main(String[] args) {
		SignalResource re = new SignalResource();
		re.setId(1);
		re.setIdentifier("BREAKOUT30DAY1");
		re.setName("Breakout 30 Day 1");
		try {
			System.out.println(DJson.serializePretty(re));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	} 
	
	
	
	

}
