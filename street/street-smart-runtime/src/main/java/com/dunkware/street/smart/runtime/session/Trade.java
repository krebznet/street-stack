package com.dunkware.street.smart.runtime.session;

import java.util.List;

import com.dunkware.street.stream.model.session.TradeSpec;

public interface Trade {

	TradeSpec getType();
	
	List<TradeOrder> getEntryOrders();
	
	List<TradeOrder> getExitOrders();
	
	TradeOpener getOpener();
	
	TradeCloser getCloser();
}
