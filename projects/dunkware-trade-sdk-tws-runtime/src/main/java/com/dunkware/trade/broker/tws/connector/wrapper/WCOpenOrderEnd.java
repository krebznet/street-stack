package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCOpenOrderEnd implements EWrapperCall {

	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.openOrderEnd();
		
	}
	
	

}
