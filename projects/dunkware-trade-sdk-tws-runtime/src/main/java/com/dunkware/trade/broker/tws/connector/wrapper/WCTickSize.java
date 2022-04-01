package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCTickSize implements EWrapperCall {
	
	private int tickerId; 
	private int field; 
	private int size; 
	
	public WCTickSize(int tickerId, int field, int size) {
		this.tickerId = tickerId; 
		this.field = field; 
		this.size = size; 
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.tickSize(tickerId, field, size);
		
	}
	
	

}
