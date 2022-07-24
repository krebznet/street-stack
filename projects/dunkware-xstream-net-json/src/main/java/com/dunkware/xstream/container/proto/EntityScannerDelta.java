package com.dunkware.xstream.container.proto;

import com.dunkware.common.util.data.NetScannerDelta;

public class EntityScannerDelta {

	private String scannerId; 
	private NetScannerDelta delta;
	
	
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	}
	public NetScannerDelta getDelta() {
		return delta;
	}
	public void setDelta(NetScannerDelta delta) {
		this.delta = delta;
	} 
	
	
}
