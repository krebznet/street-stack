package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCTickGeneric implements EWrapperCall {
	
	private int tickerId; 
	private int tickType; 
	private double value; 
	
	public WCTickGeneric(int tickerId, int tickType, double value) { 
		this.tickerId = tickerId;
		this.tickType = tickType;
		this.value = value; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.tickGeneric(tickerId, tickType, value);
		
	}
	
	

}
