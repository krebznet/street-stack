package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCTickPrice implements EWrapperCall {
	
	private int tickerId; 
	private int field; 
	private double price; 
	private int canAutoExecute; 
	
	public WCTickPrice( int tickerId, int field, double price, int canAutoExecute) { 
		this.tickerId = tickerId; 
		this.field = field; 
		this.price = price; 
		this.canAutoExecute = canAutoExecute;
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.tickPrice(tickerId, field, price, canAutoExecute);
		
	}
	
	

}
