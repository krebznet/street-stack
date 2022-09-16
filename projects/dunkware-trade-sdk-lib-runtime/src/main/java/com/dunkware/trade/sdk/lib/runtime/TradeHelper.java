package com.dunkware.trade.sdk.lib.runtime;

public class TradeHelper {

	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static double subtractPercent(double value, double percent) { 
		double factor = 100 - Math.abs(percent);
		factor = factor * 0.01;
		double stopAmount = value * factor;
		return round(stopAmount,2);
	}
}
