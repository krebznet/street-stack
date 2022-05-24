package com.dunkware.xstream.net.client;

import com.dunkware.xstream.net.client.core.StreamClientImpl;

public class StreamClientFactory {
	
	public static StreamClient create() { 
		return new StreamClientImpl();
	}

}
