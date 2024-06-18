package com.dunkware.trade.service.stream.resources;

import com.dunkware.utils.core.json.DunkJson;

public class SignalResource {
	
	private long id; 
	private String ident;
	private String name;
	private String group; 
	private String created; 
	
	
	public static void main(String[] args) {
		SignalResource re = new SignalResource();
		re.setId(1);
		re.setIdent("BREAKOUT30DAY1");
		re.setName("Breakout 30 Day 1");
		try {
			System.out.println(DunkJson.serializePretty(re));
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

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	
	
	
	

}
