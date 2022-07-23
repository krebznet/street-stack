package com.dunkware.xstream.net.client;

import com.dunkware.xstream.net.client.core.ContainerClientImpl;

public class ContainerClientFactory {
	
	public static ContainerClient create() { 
		return new ContainerClientImpl();
	}

}
