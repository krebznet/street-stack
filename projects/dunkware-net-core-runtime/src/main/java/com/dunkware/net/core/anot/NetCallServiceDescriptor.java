package com.dunkware.net.core.anot;

public class NetCallServiceDescriptor {
	
	private Class clazz; 
	private String endpoint; 
	
	public NetCallServiceDescriptor() { 
		
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	

}
