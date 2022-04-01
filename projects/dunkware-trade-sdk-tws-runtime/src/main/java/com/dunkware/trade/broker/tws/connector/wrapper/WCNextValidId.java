package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCNextValidId implements EWrapperCall{
	
	private int id; 
	
	public WCNextValidId(int validId) { 
		this.id = validId;
	}

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.nextValidId(id);
		
	}
	
	

}
