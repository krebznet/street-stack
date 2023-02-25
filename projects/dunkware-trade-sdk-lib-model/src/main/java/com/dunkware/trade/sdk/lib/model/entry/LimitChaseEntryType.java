package com.dunkware.trade.sdk.lib.model.entry;

import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.lib.model.constants.PriceType;

public class LimitChaseEntryType extends EntryType {

	private PriceType target; 
	private double offset; 
	private int interval; 
	private int timeout;
	
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
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	} 
	
	
	
	
	
}
