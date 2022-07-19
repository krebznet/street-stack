package com.dunkware.common.util.data;

public class NetScanner {
	
	// delay update interval for deltas? 
	public static NetScanner newInstance(NetList list, String keyId) { 
		return new NetScanner(list, keyId);
	}
	

	public static NetScanner newInstance(NetList list, String keyId, int deltaThrottle) { 
		return new NetScanner(list, keyId);
	}
	
	private NetList list; 
	private String keyId; 
	
	private NetScanner(NetList list, String keyId) { 
		this.list = list; 
		this.keyId = keyId; 
	}
	
	

}
