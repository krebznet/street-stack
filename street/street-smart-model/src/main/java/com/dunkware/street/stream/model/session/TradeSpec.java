package com.dunkware.street.stream.model.session;

import java.util.List;

//TODO: AVINASHANV-33 Trade Spec
/**
 * this is JSON serialiable model for creating a trade, it defines what ticker
 * the type of ticker equity, crypto etc. the trade side long or short, a list 
 * and then allocated capital to define how much money to put on the trade this
 * is used in Trade Strategy where trade strategy has all the exit logic and open
 * closer logic defined. 
 */
public interface TradeSpec {

	String getTicker();
	
	String getTickerType();
	
	String getTradeSide();
	
	double getAllocatedCapital();
	
	
}
