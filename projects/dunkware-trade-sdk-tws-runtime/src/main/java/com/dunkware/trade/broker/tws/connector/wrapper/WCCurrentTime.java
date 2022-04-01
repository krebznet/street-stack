package com.dunkware.trade.broker.tws.connector.wrapper;

import com.ib.client.EWrapper;

public class WCCurrentTime implements EWrapperCall {

	private long _time;
	
	public WCCurrentTime(long time) { 
		_time = time; 
	}
	@Override
	public void callMethod(EWrapper wrapper) {
		wrapper.currentTime(_time);
	}
	
	

}
