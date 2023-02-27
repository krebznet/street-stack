package com.dunkware.trade.sdk.lib.model.entry;

import com.dunkware.trade.sdk.lib.model.constants.PriceType;

public class LimitAdjustEntryType {

	private PriceType targetPrice; 
	private int entryTimeout; 
	private int adjustInterval;
	private double targetOffset; // percent offset if you want to say buy 1% above asking price 
	
	// buy on the as
	
}
