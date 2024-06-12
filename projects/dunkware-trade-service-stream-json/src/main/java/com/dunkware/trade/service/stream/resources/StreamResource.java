package com.dunkware.trade.service.stream.resources;

import com.dunkware.utils.core.json.DunkJson;

public class StreamResource {
	
	private String name; 
	private String identifier; 
	private long id; 
	
	private double version;
	
	public static void main(String[] args) {
		StreamResource re = new StreamResource();
		re.setId(3);
		re.setName("US Equities");
		re.setIdentifier("us_equity");
		
		try {
			System.out.println(DunkJson.serializePretty(re));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	} 
	
	
	
	

}
