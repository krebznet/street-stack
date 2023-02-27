package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeSpec;
import com.dunkware.trade.sdk.core.model.trade.TradeStatus;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.tick.api.instrument.Instrument;

public interface Trade {
	
	void open() throws Exception;
	
	void discard() throws Exception;
	
	void close() throws Exception;
	
	TradeSpec getSpec();
	
	DEventNode getEventNode();
	
	TradeType getType();
	
	String getSymbol();
	
	TradeStatus getStatus();
	
	TradeEntry getEntry();
	
	TradeExit getExit();
	
	Instrument getInstrument();

	Order createEntryOrder(OrderType type) throws Exception;
	
	Order createExitOrder(OrderType type) throws Exception;
	
	TradeContext getContext();
	
}
