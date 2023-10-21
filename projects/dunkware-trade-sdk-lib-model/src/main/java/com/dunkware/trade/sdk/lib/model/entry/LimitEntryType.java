package com.dunkware.trade.sdk.lib.model.entry;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.lib.model.constants.PriceType;

public class LimitEntryType extends EntryType {

	private int timeout;
	private PriceType target; 
	private double offset; 
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public PriceType getTarget() {
		return target;
	}

	public void setTarget(PriceType target) {
		this.target = target;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	} 
	
	
	
	
	
	
	
	
	
}
