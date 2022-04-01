package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCTickString implements EWrapperCall {
	
	int tickerId;
	int tickType;
	 String value;
	
	public WCTickString(int tickerId, int tickType, String value) {
		this.tickerId = tickerId;
		this.tickType = tickType;
		this.value = value; 
				
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.tickString(tickerId, tickType, value);
		
	}
	
	

}
