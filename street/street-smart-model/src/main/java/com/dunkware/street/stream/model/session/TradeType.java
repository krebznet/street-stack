package com.dunkware.street.stream.model.session;

import java.util.List;

public interface TradeType {

	String getTicker();
	
	String getTickerType();
	
	String getTradeSide();
	
	TradeOpenerType getOpener();
	
	TradeCloserType getCloser();
	
	List<TradeExitType> getExits();
	
	double getAllocatedCapital();
	
	
}
