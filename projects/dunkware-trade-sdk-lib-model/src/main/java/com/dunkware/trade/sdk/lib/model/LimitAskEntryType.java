package com.dunkware.trade.sdk.lib.model;

import com.dunkware.trade.sdk.core.model.trade.EntryType;

public class LimitAskEntryType extends EntryType {

	private int timeout;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	} 
	
	
	
}
