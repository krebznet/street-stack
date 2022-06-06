package com.dunkware.net.core.anot;

import com.dunkware.net.core.service.NetServiceException;
import com.dunkware.net.core.service.NetChannelService;

public class NetChannelServiceDescriptor {
	
	private Class clazz; 
	private String endpoint; 
	
	public NetChannelServiceDescriptor() { 
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
